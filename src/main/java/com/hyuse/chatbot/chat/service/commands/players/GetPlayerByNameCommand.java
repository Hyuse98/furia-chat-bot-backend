package com.hyuse.chatbot.chat.service.commands.players;

import com.hyuse.chatbot.chat.model.ChatMessage;
import com.hyuse.chatbot.chat.service.BaseCommand;
import com.hyuse.chatbot.chat.service.CommandHandler;
import com.hyuse.chatbot.player.model.Player;
import com.hyuse.chatbot.player.service.PlayerServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetPlayerByNameCommand extends BaseCommand {

    private final PlayerServiceInterface service;

    @Autowired
    public GetPlayerByNameCommand(PlayerServiceInterface service) {
        this.service = service;
    }

    @Override
    public boolean supports(String command) {
        return command.startsWith("NamePlayer ");
    }

    @Override
    public ChatMessage handle(String input) {
        String[] parts = input.split("\\s+", 2);

        if (parts.length < 2 || parts[1].isBlank()) {
            return createResponse("Uso correto: 'player <nome>'");
        }

        String name = parts[1].trim();
        Player player = service.getPlayerByName(name);

        if (player == null) {
            return createResponse("Jogador não encontrado: **" + name + "**");
        }

        StringBuilder response = new StringBuilder("Player: **");
        response.append(player.getName());
        response.append("** - Time: ");
        response.append(player.getTeam());
        return createResponse(response.toString());
    }
}
