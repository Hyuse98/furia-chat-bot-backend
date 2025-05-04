package com.hyuse.chatbot.chat.service;

import com.hyuse.chatbot.chat.model.ChatMessage;
import com.hyuse.chatbot.chat.model.MessageType;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChatService {

    private static final String BOT_ID = "Assistant";
    private final List<CommandHandler> handlers;
    private final NaturalLanguageProcessor nlProcessor;

    public ChatService(List<CommandHandler> handlers, NaturalLanguageProcessor nlProcessor) {
        this.handlers = handlers;
        this.nlProcessor = nlProcessor;
    }

    public ChatMessage processMessage(ChatMessage input) {
        if (input == null || input.getMessage() == null) {
            return createErrorResponse("Mensagem inválida.");
        }

        String text = input.getMessage().trim();
        String userId = input.getUserId();

        if (text.startsWith("/")) {
            String command = text.substring(1).trim();
            return handleCommand(command, userId);
        }

        String nlResponse = nlProcessor.processMessage(text);
        if (nlResponse != null) {
            return createBotResponse(nlResponse);
        }

        return createBotResponse(
                "Não entendi sua mensagem. Digite /help para ver os comandos disponíveis."
        );
    }

    private ChatMessage handleCommand(String command, String userId) {
        if (command.equalsIgnoreCase("help")) {
            return getHelpMessage();
        }

        return handlers.stream()
                .filter(handler -> handler.supports(command))
                .findFirst()
                .map(handler -> {
                    try {
                        return handler.handle(command);
                    } catch (Exception e) {
                        return createErrorResponse(
                                "Erro ao processar o comando: " + e.getMessage()
                        );
                    }
                })
                .orElse(createErrorResponse("Comando não reconhecido. Digite /help para ajuda."));
    }

    private ChatMessage getHelpMessage() {
        String help = """
    <div class="help-container">
        <div class="help-header">
            <div class="help-icon">💬</div>
            <h3 class="help-title">Comandos Disponíveis</h3>
        </div>

        <div class="help-section">
            <h4 class="section-header">Comandos Gerais</h4>
            <hr>
            <div class="command-item" data-command="/help" onclick="executeCommand('/help')">
                <span class="command-name">❓ /help</span>
                <span class="command-desc">Mostra esta mensagem de ajuda</span>
            </div>
            <hr>
        </div>

        <div class="help-section">
            <h4 class="section-header">Partidas</h4>
            <hr>
            <div class="command-item" data-command="/matches" onclick="executeCommand('/matches')">
                <span class="command-name">📅 /matches</span>
                <span class="command-desc">Lista as próximas partidas</span>
            </div>
            <div class="command-item" data-command="/match [id]" onclick="promptMatchId()">
                <span class="command-name">🎮 /match [id]</span>
                <span class="command-desc">Mostra detalhes de uma partida específica</span>
            </div>
            <div class="command-item" data-command="/ScheduledMatches" onclick="executeCommand('/ScheduledMatches')">
                <span class="command-name">⏳ /ScheduledMatches</span>
                <span class="command-desc">Busca por Partidas Agendadas</span>
            </div>
            <div class="command-item" data-command="/ClosedMatches" onclick="executeCommand('/ClosedMatches')">
                <span class="command-name">✅ /ClosedMatches</span>
                <span class="command-desc">Busca por Partidas Encerradas</span>
            </div>
            <div class="command-item" data-command="/LiveMatches" onclick="executeCommand('/LiveMatches')">
                <span class="command-name">🔴 /LiveMatches</span>
                <span class="command-desc">Busca por Partidas Ao Vivo</span>
            </div>
            <hr>
        </div>

        <div class="help-section">
            <h4 class="section-header">Times</h4>
            <hr>
            <div class="command-item" data-command="/team [tag]" onclick="promptTeamTag()">
                <span class="command-name">👥 /team [tag]</span>
                <span class="command-desc">Busca informações sobre um time</span>
            </div>
            <div class="command-item" data-command="/TagTeam [tag]" onclick="promptTagTeam()">
                <span class="command-name">🏷️ /TagTeam [tag]</span>
                <span class="command-desc">Busca dados do time pela Tag</span>
            </div>
            <div class="command-item" data-command="/NameTeam [nome]" onclick="promptNameTeam()">
                <span class="command-name">📝 /NameTeam [nome]</span>
                <span class="command-desc">Busca dados do time pelo Nome Completo</span>
            </div>
            <div class="command-item" data-command="/RegionTeams [região]" onclick="promptRegionTeams()">
                <span class="command-name">🌍 /RegionTeams [região]</span>
                <span class="command-desc">Lista times por Região</span>
            </div>
            <hr>
        </div>

        <div class="help-section">
            <h4 class="section-header">Jogadores</h4>
            <hr>
            <div class="command-item" data-command="/player [nome]" onclick="promptPlayerName()">
                <span class="command-name">👤 /player [nome]</span>
                <span class="command-desc">Busca informações sobre um jogador</span>
            </div>
            
            <div class="command-item" data-command="/NamePlayer [nome]" onclick="promptNamePlayer()">
                <span class="command-name">🆔 /NamePlayer [nome]</span>
                <span class="command-desc">Busca Jogador pelo Nome</span>
            </div>
            
            <div class="command-item" data-command="/TeamPlayers [tag do time]" onclick="promptTeamPlayers()">
                <span class="command-name">🧑‍🤝‍🧑 /TeamPlayers [tag do time]</span>
                <span class="command-desc">Lista Jogadores por Time</span>
            </div>
        </div>

        <div class="help-footer">
            Digite na caixa de mensagem um comando para usalo!
        </div>
    </div>
    """;

        ChatMessage response = createBotResponse(help);
        response.setIsHtml(true);
        return response;
    }

    private ChatMessage createBotResponse(String message) {
        return ChatMessage.builder()
                .userId(BOT_ID)
                .message(message)
                .type(MessageType.BOT_RESPONSE)
                .build();
    }

    private ChatMessage createErrorResponse(String message) {
        return ChatMessage.builder()
                .userId(BOT_ID)
                .message(message)
                .type(MessageType.BOT_RESPONSE)
                .build();
    }
}
