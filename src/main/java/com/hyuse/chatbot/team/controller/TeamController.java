package com.hyuse.chatbot.team.controller;

import com.hyuse.chatbot.team.model.Team;
import com.hyuse.chatbot.team.model.dto.TeamDTO;
import com.hyuse.chatbot.team.service.TeamServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamServiceInterface teamService;

    @Autowired
    public TeamController(TeamServiceInterface teamService) {
        this.teamService = teamService;
    }

    @PostMapping
    public ResponseEntity<EntityModel<Team>> createTeam(@RequestBody TeamDTO teamDTO) {
        teamService.createTeam(teamDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity.BodyBuilder updateTeam(@PathVariable Long id, @RequestBody TeamDTO teamDTO) {
        teamService.updateTeam(id, teamDTO);
        Team updatedTeam = teamService.getTeamById(id);
        return ResponseEntity.ok();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        teamService.deleteTeamById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
