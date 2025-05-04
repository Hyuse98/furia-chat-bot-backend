package com.hyuse.chatbot.chat.service.commands.teams;

import com.hyuse.chatbot.chat.model.ChatMessage;
import com.hyuse.chatbot.chat.service.BaseCommand;
import com.hyuse.chatbot.team.model.Team;
import com.hyuse.chatbot.team.service.TeamServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ListTeamByRegionCommand extends BaseCommand {

    private final TeamServiceInterface service;

    @Override
    public boolean supports(String command) {
        return command.startsWith("RegionTeams ");
    }

    @Override
    public ChatMessage handle(String input) {

        String region = input.substring("RegionTeams ".length()).trim();
        try {
            List<Team> teams = service.listTeamsByCountry(region);

            if (teams == null || teams.isEmpty()) {
                return createResponse("Nenhuma equipe encontrada na região: **" + region + "**");
            }

            StringBuilder response = new StringBuilder("Equipes na região **" + region + "**: \n");
            response.append("<hr>");

            teams.forEach(team -> {
                response.append("• **").append(team.getTeamName()).append("**: ").append(team.getTeamCountry()).append("\n");
                response.append("<hr>");
            });

            return createResponse(response.toString());
        } catch (Exception e) {
            return createResponse("Erro ao buscar equipes.");
        }
    }
}
