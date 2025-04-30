package com.hyuse.chatbot.team.controller;

import com.hyuse.chatbot.team.model.Team;
import com.hyuse.chatbot.team.model.dto.TeamDTO;
import com.hyuse.chatbot.team.service.TeamServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamServiceInterface teamService;
    private final PagedResourcesAssembler<Team> pagedResourcesAssembler;

    @Autowired
    public TeamController(TeamServiceInterface teamService, PagedResourcesAssembler<Team> pagedResourcesAssembler) {
        this.teamService = teamService;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @PostMapping
    public ResponseEntity<EntityModel<Team>> createTeam(@RequestBody TeamDTO teamDTO) {
        teamService.createTeam(teamDTO);
        Team team = teamService.getTeamByName(teamDTO.teamName()); // Busca o time criado para retornar com HATEOAS
        EntityModel<Team> resource = EntityModel.of(team,
                linkTo(methodOn(TeamController.class).getTeamById(team.getId())).withSelfRel(),
                linkTo(methodOn(TeamController.class).listTeams(Pageable.unpaged(), pagedResourcesAssembler)).withRel("teams"));
        return new ResponseEntity<>(resource, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Team>> updateTeam(@PathVariable Long id, @RequestBody TeamDTO teamDTO) {
        teamService.updateTeam(id, teamDTO);
        Team updatedTeam = teamService.getTeamById(id);
        EntityModel<Team> resource = EntityModel.of(updatedTeam,
                linkTo(methodOn(TeamController.class).getTeamById(id)).withSelfRel(),
                linkTo(methodOn(TeamController.class).listTeams(Pageable.unpaged(), pagedResourcesAssembler)).withRel("teams"));
        return ResponseEntity.ok(resource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        teamService.deleteTeamById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Team>> getTeamById(@PathVariable Long id) {
        Team team = teamService.getTeamById(id);
        EntityModel<Team> resource = EntityModel.of(team,
                linkTo(methodOn(TeamController.class).getTeamById(id)).withSelfRel(),
                linkTo(methodOn(TeamController.class).listTeams(Pageable.unpaged(), pagedResourcesAssembler)).withRel("teams"));
        return ResponseEntity.ok(resource);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<EntityModel<Team>> getTeamByName(@PathVariable String name) {
        Team team = teamService.getTeamByName(name);
        EntityModel<Team> resource = EntityModel.of(team,
                linkTo(methodOn(TeamController.class).getTeamByName(name)).withSelfRel(),
                linkTo(methodOn(TeamController.class).listTeams(Pageable.unpaged(), pagedResourcesAssembler)).withRel("teams"));
        return ResponseEntity.ok(resource);
    }

    @GetMapping("/tag/{tag}")
    public ResponseEntity<EntityModel<Team>> getTeamByTag(@PathVariable String tag) {
        Team team = teamService.getTeamByTeamTag(tag);
        EntityModel<Team> resource = EntityModel.of(team,
                linkTo(methodOn(TeamController.class).getTeamByTag(tag)).withSelfRel(),
                linkTo(methodOn(TeamController.class).listTeams(Pageable.unpaged(), pagedResourcesAssembler)).withRel("teams"));
        return ResponseEntity.ok(resource);
    }

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<Team>>> listTeams(Pageable pageable, PagedResourcesAssembler<Team> assembler) {
        Page<Team> teams = teamService.listTeams(pageable);
        return ResponseEntity.ok(assembler.toModel(teams, team -> EntityModel.of(team,
                linkTo(methodOn(TeamController.class).getTeamById(team.getId())).withSelfRel())));
    }

    @GetMapping("/country/{country}")
    public ResponseEntity<PagedModel<EntityModel<Team>>> listTeamsByCountry(@PathVariable String country, Pageable pageable, PagedResourcesAssembler<Team> assembler) {
        Page<Team> teams = teamService.listTeamsByCountry(country, pageable);
        return ResponseEntity.ok(assembler.toModel(teams, team -> EntityModel.of(team,
                linkTo(methodOn(TeamController.class).getTeamById(team.getId())).withSelfRel())));
    }
}
