package com.hyuse.chatbot.chat.service;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Processador simples de linguagem natural para o bot
 * Em uma implementação real, usar uma biblioteca NLP
 * OpenNLP, Stanford NLP ou integrar com serviços como Dialogflow
 */
@Component
public class NaturalLanguageProcessor {

    private final Map<Pattern, String> patterns = new HashMap<>();

    public NaturalLanguageProcessor() {
        patterns.put(Pattern.compile("(?i).*\\b(oi|olá|ola|hey|e aí|e ai)\\b.*"),
                "Olá! Como posso ajudar? Digite /help para ver os comandos disponíveis.");

        patterns.put(Pattern.compile("(?i).*\\b(tchau|adeus|até mais|ate mais|até logo|ate logo)\\b.*"),
                "Até mais! Foi um prazer ajudar.");

        patterns.put(Pattern.compile("(?i).*\\b(obrigad[oa]|valeu|thanks)\\b.*"),
                "De nada! Estou aqui para ajudar.");

        patterns.put(Pattern.compile("(?i).*\\b(partida[s]|jogo|match|confronto)\\b.*"),
                "Parece que você está procurando informações sobre partidas. Use /matches para ver as próximas partidas ou /match [id] para ver detalhes de uma partida específica.");

        patterns.put(Pattern.compile("(?i).*\\b(equipe|time|team)\\b.*"),
                "Para ver informações sobre times, use o comando /team [tag]. Por exemplo: /team mibr");

        patterns.put(Pattern.compile("(?i).*\\b(jogador|player|atleta)\\b.*"),
                "Para buscar informações sobre jogadores, use o comando /player [nome]. Por exemplo: /player fallen");

        patterns.put(Pattern.compile("(?i).*\\b(ajuda|help|comando|command)\\b.*"),
                "Para ver a lista de comandos disponíveis, digite /help");
    }

    /**
     * Processa uma mensagem em linguagem natural e tenta encontrar uma resposta adequada
     * @param message A mensagem do usuário
     * @return Uma resposta apropriada ou null se não reconhecer o padrão
     */
    public String processMessage(String message) {
        if (message == null || message.trim().isEmpty()) {
            return null;
        }

        for (Map.Entry<Pattern, String> entry : patterns.entrySet()) {
            if (entry.getKey().matcher(message).matches()) {
                return entry.getValue();
            }
        }
        return null;
    }

    /**
     * Adiciona um novo padrão de reconhecimento
     * @param regex Expressão regular para reconhecer
     * @param response Resposta a ser retornada quando esse padrão for reconhecido
     */
    public void addPattern(String regex, String response) {
        patterns.put(Pattern.compile(regex), response);
    }
}
