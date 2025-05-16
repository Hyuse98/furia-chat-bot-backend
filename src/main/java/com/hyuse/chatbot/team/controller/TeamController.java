package com.hyuse.chatbot.team.controller;

import com.hyuse.chatbot.team.model.dto.TeamDTO;
import com.hyuse.chatbot.team.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TeamDTO> createTeam(@RequestBody TeamDTO teamDTO) {
        TeamDTO createdTeam = teamService.create(teamDTO);
        return new ResponseEntity<>(createdTeam, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamDTO> getTeamById(@PathVariable Long id) {
        return new ResponseEntity<>(teamService.getById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TeamDTO>> getAllTeams() {
        List<TeamDTO> teams = teamService.getAll();
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TeamDTO> updateTeam(@PathVariable Long id, @RequestBody TeamDTO teamDTO) {
        TeamDTO updatedTeam = teamService.update(teamDTO, id);
        return new ResponseEntity<>(updatedTeam, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        teamService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<TeamDTO> getTeamByName(@PathVariable String name) {
        try {
            TeamDTO team = teamService.getByName(name);
            return new ResponseEntity<>(team, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/tag/{tag}")
    public ResponseEntity<TeamDTO> getTeamByTag(@PathVariable String tag) {
        try {
            TeamDTO team = teamService.getByTag(tag);
            return new ResponseEntity<>(team, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/region/{region}")
    public ResponseEntity<List<TeamDTO>> getTeamsByRegion(@PathVariable String region) {
        List<TeamDTO> teams = teamService.getByRegion(region);
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }
}
