package com.hyuse.chatbot.player.service;

import com.hyuse.chatbot.player.model.dto.PlayerDTO;
import com.hyuse.chatbot.player.model.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlayerServiceInterface {

    void createPlayer(PlayerDTO playerDTO);

    void updatePlayer(PlayerDTO playerDTO, Long id);

    Player getPlayerById(Long id);

    Player getPlayerByName(String name);

    List<Player> listPlayersByTeam(String team);

    void deletePlayerById(Long id);
}
