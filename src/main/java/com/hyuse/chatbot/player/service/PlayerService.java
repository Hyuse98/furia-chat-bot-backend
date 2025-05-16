package com.hyuse.chatbot.player.service;

import com.hyuse.chatbot.core.util.CrudService;
import com.hyuse.chatbot.player.model.dto.PlayerDTO;

import java.util.List;

public interface PlayerService extends CrudService<PlayerDTO, Long> {

    PlayerDTO getByUsername(String name);
    PlayerDTO getByName(String name);
    List<PlayerDTO> getByTeam(String name);
}
