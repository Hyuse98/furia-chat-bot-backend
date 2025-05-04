package com.hyuse.chatbot.chat.service.commands.matches;

import com.hyuse.chatbot.chat.model.ChatMessage;
import com.hyuse.chatbot.chat.service.BaseCommand;
import com.hyuse.chatbot.match.model.Match;
import com.hyuse.chatbot.match.model.MatchStatus;
import com.hyuse.chatbot.match.service.MatchServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ListScheduledMatchesCommand extends BaseCommand {

    private final MatchServiceInterface service;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    @Override
    public boolean supports(String command) {
        return command.equalsIgnoreCase("ScheduledMatches");
    }

    @Override
    public ChatMessage handle(String userId) {

        try {
            List<Match> list = service.listByMatchStatus(MatchStatus.SCHEDULED);

            if (list.isEmpty())
                return createResponse("Nenhuma partida encontrada.");

            StringBuilder response = new StringBuilder("📅 **Próximas Partidas:**\n\n");
            response.append("<hr>");

            list.forEach(match -> {
                response.append("🏆 **").append(match.getTeamA()).append("** vs **")
                        .append(match.getTeamB()).append("**\n");
                response.append("📆 ").append(match.getDateHour().format(DATE_FORMATTER)).append("\n");
                response.append("🔄 Status: **").append(formatStatus(match)).append("**\n\n");
                response.append("<hr>\n");
            });

            return createResponse(response.toString().trim());
        } catch (Exception e) {
            return createErrorResponse("Não foi possível listar as partidas: " + e.getMessage());
        }
    }

    private String formatStatus(Match match) {
        return match.getMatchStatus() != null ? match.getMatchStatus().toString() : "Não definido";
    }

    @Override
    public String getHelpText() {
        return "/matches - Lista as próximas 5 partidas agendadas";
    }
}
