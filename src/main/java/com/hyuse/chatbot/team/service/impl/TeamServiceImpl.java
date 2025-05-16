package com.hyuse.chatbot.team.service.impl;

import com.hyuse.chatbot.team.mapper.TeamMapper;
import com.hyuse.chatbot.team.model.Team;
import com.hyuse.chatbot.team.model.dto.TeamDTO;
import com.hyuse.chatbot.team.repository.TeamRepository;
import com.hyuse.chatbot.team.service.TeamService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final TeamMapper mapper;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository, TeamMapper mapper) {
        this.teamRepository = teamRepository;
        this.mapper = mapper;
    }

    @Override
    public TeamDTO create(TeamDTO teamDTO) {

        Optional<Team> existTeam = teamRepository.findByTeamName(teamDTO.teamName());

        if (existTeam.isPresent()) {
            throw new EntityExistsException("Team with name " + existTeam.get().getTeamName() + " already exists");
        }

        var team = mapper.toEntity(teamDTO);
        teamRepository.save(team);

        return teamDTO;
    }

    @Override
    public TeamDTO getById(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("Id null");
        }

        Optional<Team> team = teamRepository.findById(id);

        if (team.isEmpty()) {
            throw new EntityNotFoundException("Team Not Found");
        }

        return mapper.toDto(team.get());
    }

    @Override
    public List<TeamDTO> getAll() {

        return teamRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TeamDTO update(TeamDTO teamDTO, Long id) {

        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }

        if (!teamRepository.existsById(id)) {
            throw new EntityNotFoundException("Team with id " + id + " not found");
        }

        Team teamToUpdate = mapper.toEntity(teamDTO);
        teamToUpdate.setId(id);

        Team updatedTeam = teamRepository.save(teamToUpdate);
        return mapper.toDto(updatedTeam);
    }

    @Override
    public void deleteById(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("id null");
        }

        if (!teamRepository.existsById(id)) {
            throw new EntityNotFoundException("Team no found");
        }

        teamRepository.deleteById(id);
    }

    @Override
    public TeamDTO getByName(String name) {

        if (name.isBlank()) {
            throw new IllegalArgumentException("Name cant be blank");
        }

        var team = teamRepository.findByTeamName(name)
                .orElseThrow(() -> new EntityNotFoundException("Time não encontrado com o nome: " + name));

        return mapper.toDto(team);
    }

    @Override
    public TeamDTO getByTag(String tag) {

        if (tag.isBlank()) {
            throw new IllegalArgumentException("Tag cant be blank");
        }

        var team = teamRepository.findByTeamTag(tag)
                .orElseThrow(() -> new EntityNotFoundException("Time não encontrado com a tag: " + tag));

        return mapper.toDto(team);
    }

    @Override
    public List<TeamDTO> getByRegion(String country) {
        return teamRepository.findByTeamRegion(country)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
