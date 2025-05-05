package com.hyuse.chatbot.chat.service.commands.teams;

import com.hyuse.chatbot.chat.model.ChatMessage;
import com.hyuse.chatbot.chat.service.BaseCommand;
import com.hyuse.chatbot.chat.service.CommandHandler;
import com.hyuse.chatbot.team.model.Team;
import com.hyuse.chatbot.team.service.TeamServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetTeamByNameCommand extends BaseCommand {

    private final TeamServiceInterface service;

    @Override
    public boolean supports(String command) {
        return command.startsWith("NameTeam ");
    }

    @Override
    public ChatMessage handle(String input) {

        String name = input.substring("NameTeam ".length()).trim();
        try {
            Team team = service.getTeamByName(name);
            StringBuilder response = new StringBuilder("Equipe: **");
            response.append(team.getTeamName());
            response.append("**, país: ");
            response.append(team.getTeamRegion());
            response.append(", tag: **");
            response.append(team.getTeamTag());
            response.append("**");
            return createResponse(response.toString());
        } catch (Exception e) {
            return createResponse("Equipe não encontrada.");
        }
    }
}
