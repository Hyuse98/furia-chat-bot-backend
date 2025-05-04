package com.hyuse.chatbot.chat.service.commands.players;

import com.hyuse.chatbot.chat.model.ChatMessage;
import com.hyuse.chatbot.chat.service.BaseCommand;
import com.hyuse.chatbot.chat.service.CommandHandler;
import com.hyuse.chatbot.player.model.Player;
import com.hyuse.chatbot.player.service.PlayerServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListPlayerByTeamCommand extends BaseCommand {

    private final PlayerServiceInterface service;

    @Autowired
    public ListPlayerByTeamCommand(PlayerServiceInterface service) {
        this.service = service;
    }

    @Override
    public boolean supports(String command) {
        return command.startsWith("TeamPlayers ");
    }

    @Override
    public ChatMessage handle(String input) {
        String[] parts = input.split("\\s+", 2);

        if (parts.length < 2 || parts[1].isBlank()) {
            return createResponse("Uso correto: 'playersByTeam <team>'");
        }

        String team = parts[1].trim();
        List<Player> players = service.listPlayersByTeam(team);

        if (players == null || players.isEmpty()) {
            return createResponse("Nenhum jogador encontrado para o time: " + team);
        }

        StringBuilder sb = new StringBuilder("Jogadores do time **" + team + "**: \n");
        sb.append("<hr>");
        for (Player jogador : players) {
            sb.append("• **").append(jogador.getName()).append("**: ").append(jogador.getTeam()).append("\n");
        }

        return createResponse(sb.toString());
    }
}
