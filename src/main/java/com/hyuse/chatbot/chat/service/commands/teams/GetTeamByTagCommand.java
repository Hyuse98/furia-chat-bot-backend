package com.hyuse.chatbot.chat.service.commands.teams;

import com.hyuse.chatbot.chat.model.ChatMessage;
import com.hyuse.chatbot.chat.service.BaseCommand;
import com.hyuse.chatbot.team.model.Team;
import com.hyuse.chatbot.team.service.TeamServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetTeamByTagCommand extends BaseCommand {

    private final TeamServiceInterface service;

    @Override
    public boolean supports(String command) {
        return command.startsWith("TagTeam ");
    }

    @Override
    public ChatMessage handle(String input) {
        String tag = input.substring("TagTeam ".length()).trim();

        try {
            Team team = service.getTeamByTeamTag(tag);
            StringBuilder response = new StringBuilder("Equipe: **");
            response.append(team.getTeamName());
            response.append("** - País: ");
            response.append(team.getTeamCountry());
            return createResponse(response.toString());
        } catch (Exception e) {
            return createResponse("Equipe não encontrada.");
        }
    }
}
