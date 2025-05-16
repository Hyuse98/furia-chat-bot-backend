package com.hyuse.chatbot.team.service;

import com.hyuse.chatbot.core.util.CrudService;
import com.hyuse.chatbot.team.model.dto.TeamDTO;

import java.util.List;

public interface TeamService extends CrudService<TeamDTO, Long> {

    TeamDTO getByName(String name);

    TeamDTO getByTag(String name);

    List<TeamDTO> getByRegion(String country);
}
