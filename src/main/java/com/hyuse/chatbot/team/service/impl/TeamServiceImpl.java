package com.hyuse.chatbot.team.service.impl;

import com.hyuse.chatbot.team.model.Team;
import com.hyuse.chatbot.team.model.dto.TeamDTO;
import com.hyuse.chatbot.team.repository.TeamRepository;
import com.hyuse.chatbot.team.service.TeamServiceInterface;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamServiceInterface {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public void createTeam(TeamDTO teamDTO) {
        Team team = new Team(teamDTO.teamName(), teamDTO.teamTag(), teamDTO.teamRegion(), teamDTO.players(), teamDTO.hltvUrl());
        teamRepository.save(team);
    }

    @Override
    public void updateTeam(Long id, TeamDTO teamDTO) {
        Team existingTeam = teamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Time não encontrado com o ID: " + id));

        existingTeam.setTeamName(teamDTO.teamName());
        existingTeam.setTeamTag(teamDTO.teamTag());
        existingTeam.setTeamRegion(teamDTO.teamRegion());
        teamRepository.save(existingTeam);
    }

    @Override
    public void deleteTeamById(Long id) {
        if (!teamRepository.existsById(id)) {
            throw new EntityNotFoundException("Time não encontrado com o ID: " + id);
        }
        teamRepository.deleteById(id);
    }

    @Override
    public Team getTeamById(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Time não encontrado com o ID: " + id));
    }

    @Override
    public Team getTeamByName(String name) {
        return teamRepository.findByTeamName(name)
                .orElseThrow(() -> new EntityNotFoundException("Time não encontrado com o nome: " + name));
    }

    @Override
    public Team getTeamByTeamTag(String tag) {
        return teamRepository.findByTeamTag(tag)
                .orElseThrow(() -> new EntityNotFoundException("Time não encontrado com a tag: " + tag));
    }

    @Override
    public List<Team> listTeams() {
        return teamRepository.findAll();
    }

    @Override
    public List<Team> listTeamsByCountry(String country) {
        return teamRepository.findByTeamRegion(country);
    }
}
