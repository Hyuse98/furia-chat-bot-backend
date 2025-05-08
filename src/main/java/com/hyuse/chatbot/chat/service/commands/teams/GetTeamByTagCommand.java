package com.hyuse.chatbot.chat.service.commands.teams;

import com.hyuse.chatbot.chat.model.ChatMessage;
import com.hyuse.chatbot.chat.service.BaseCommand;
import com.hyuse.chatbot.team.model.dto.TeamDTO;
import com.hyuse.chatbot.team.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetTeamByTagCommand extends BaseCommand {

    private final TeamService service;

    @Override
    public boolean supports(String command) {
        return command.startsWith("TagTeam ");
    }

    @Override
    public ChatMessage handle(String input) {
        String tag = input.substring("TagTeam ".length()).trim();

        try {
            TeamDTO team = service.getByTag(tag);
            StringBuilder response = new StringBuilder("Equipe: **");
            response.append(team.teamName());
            response.append("** - País: ");
            response.append(team.teamRegion());
            return createResponse(response.toString());
        } catch (Exception e) {
            return createResponse("Equipe não encontrada.");
        }
    }
}
