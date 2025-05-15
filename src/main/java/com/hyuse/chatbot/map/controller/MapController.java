package com.hyuse.chatbot.map.controller;

import com.hyuse.chatbot.map.model.dto.MapDTO;
import com.hyuse.chatbot.map.service.MapService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maps")
public class MapController {

    private final MapService mapService;

    @Autowired
    public MapController(MapService mapService) {
        this.mapService = mapService;
    }

    @PostMapping
    public ResponseEntity<MapDTO> createMap(@Valid @RequestBody MapDTO mapDTO) {
        try {
            MapDTO createdMap = mapService.create(mapDTO);
            return new ResponseEntity<>(createdMap, HttpStatus.CREATED);
        } catch (EntityExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MapDTO> getMapById(@PathVariable Long id) {
        try {
            MapDTO mapDTO = mapService.getById(id);
            return ResponseEntity.ok(mapDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<MapDTO>> getAllMaps() {
        List<MapDTO> mapDTOs = mapService.getAll();
        return ResponseEntity.ok(mapDTOs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MapDTO> updateMap(@PathVariable Long id, @Valid @RequestBody MapDTO mapDTO) {
        try {
            MapDTO updatedMap = mapService.update(mapDTO, id);
            return ResponseEntity.ok(updatedMap);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMap(@PathVariable Long id) {
        try {
            mapService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
