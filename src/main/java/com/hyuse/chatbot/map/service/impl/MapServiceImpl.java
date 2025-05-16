package com.hyuse.chatbot.map.service.impl;

import com.hyuse.chatbot.map.mapper.MapMapper;
import com.hyuse.chatbot.map.model.Map;
import com.hyuse.chatbot.map.model.dto.MapDTO;
import com.hyuse.chatbot.map.repository.MapRepository;
import com.hyuse.chatbot.map.service.MapService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MapServiceImpl implements MapService {

    private final MapRepository mapRepository;
    private final MapMapper mapper;

    public MapServiceImpl(MapRepository mapRepository, MapMapper mapper) {
        this.mapRepository = mapRepository;
        this.mapper = mapper;
    }

    @Override
    public MapDTO create(MapDTO mapDTO) {

        Optional<Map> mapExist = mapRepository.findByMapName(mapDTO.mapName());

        if (mapExist.isPresent()) {
            throw new EntityExistsException("Map already registered");
        }

        mapRepository.save(mapper.toEntity(mapDTO));

        return mapDTO;
    }

    @Override
    public MapDTO getById(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("Id cant be null");
        }

        Optional<Map> map = mapRepository.findById(id);

        if (map.isEmpty()) {
            throw new EntityNotFoundException("Map not found");
        }

        return mapper.toDto(map.get());
    }

    @Override
    public List<MapDTO> getAll() {

        return mapRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MapDTO update(MapDTO mapDTO, Long id) {

        if (id == null) {
            throw new IllegalArgumentException("Id cant be null");
        }

        Map updatedMap = mapper.toEntity(mapDTO);
        updatedMap.setId(id);

        mapRepository.save(updatedMap);

        return mapDTO;
    }

    @Override
    public void deleteById(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("Id cant be null");
        }

        mapRepository.deleteById(id);
    }
}
