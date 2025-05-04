package com.hyuse.chatbot.team.service;

import com.hyuse.chatbot.team.model.Team;
import com.hyuse.chatbot.team.model.dto.TeamDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TeamServiceInterface {

    void createTeam(TeamDTO teamDTO);
    void updateTeam(Long id, TeamDTO teamDTO);
    void deleteTeamById(Long id);

    Team getTeamById(Long id);
    Team getTeamByName(String name);
    Team getTeamByTeamTag(String tag);

    List<Team> listTeams();
    List<Team> listTeamsByCountry(String country);

}
