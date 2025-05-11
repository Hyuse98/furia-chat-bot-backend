package com.hyuse.chatbot.match.controller;

import com.hyuse.chatbot.match.model.dto.MatchDTO;
import com.hyuse.chatbot.match.model.Match;
import com.hyuse.chatbot.match.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/matches")
public class MatchController {

    private final MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

//    @PostMapping
//    public ResponseEntity<Void> createMatch(MatchDTO matchDTO) {
//        matchService.createMatch(matchDTO);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Void> updateMatch(@PathVariable Long id, @RequestBody MatchDTO updatedMatch) {
//        matchService.updateMatchById(id, updatedMatch);
//        return ResponseEntity.noContent().build();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<EntityModel<Match>> getMatchById(@PathVariable Long id) {
//        Match match = matchService.getMatchById(id);
//        EntityModel<Match> entityModel = EntityModel.of(match,
//                linkTo(methodOn(MatchController.class).getMatchById(id)).withSelfRel(),
//                linkTo(methodOn(MatchController.class).listMatchs(Pageable.unpaged())).withRel("matches"));
//        return ResponseEntity.ok(entityModel);
//    }
//
//    @GetMapping("/date")
//    public ResponseEntity<EntityModel<Match>> getMatchByDate(@RequestParam LocalDateTime date) {
//        Match match = matchService.getMatchByDate(date);
//        EntityModel<Match> entityModel = EntityModel.of(match,
//                linkTo(methodOn(MatchController.class).getMatchByDate(date)).withSelfRel(),
//                linkTo(methodOn(MatchController.class).listMatchs(Pageable.unpaged())).withRel("matches"));
//        return ResponseEntity.ok(entityModel);
//    }
//
//    @GetMapping
//    public ResponseEntity<PagedModel<EntityModel<Match>>> listMatchs(
//            @PageableDefault(size = 10, page = 0) Pageable pageable) {
//        Page<Match> matchesPage = matchService.listMatchs(pageable);
//
//        List<EntityModel<Match>> matchModels = matchesPage.getContent().stream()
//                .map(match -> EntityModel.of(match,
//                        linkTo(methodOn(MatchController.class).getMatchById(match.getId())).withSelfRel()))
//                .collect(Collectors.toList());
//
//        PagedModel.PageMetadata metadata = new PagedModel.PageMetadata(
//                matchesPage.getSize(),
//                matchesPage.getNumber(),
//                matchesPage.getTotalElements(),
//                matchesPage.getTotalPages());
//
//        PagedModel<EntityModel<Match>> pagedModel = PagedModel.of(matchModels, metadata,
//                linkTo(methodOn(MatchController.class).listMatchs(pageable)).withSelfRel());
//
//        return ResponseEntity.ok(pagedModel);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteMatch(@PathVariable Long id) {
//        matchService.deleteMatchById(id);
//        return ResponseEntity.noContent().build();
//    }
}
