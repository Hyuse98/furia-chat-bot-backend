package com.hyuse.chatbot.coach.service.impl;

import com.hyuse.chatbot.coach.mapper.CoachMapper;
import com.hyuse.chatbot.coach.model.Coach;
import com.hyuse.chatbot.coach.model.dto.CoachDTO;
import com.hyuse.chatbot.coach.repository.CoachRepository;
import com.hyuse.chatbot.coach.service.CoachService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CoachServiceImpl implements CoachService {

    private final CoachRepository coachRepository;
    private final CoachMapper mapper;

    public CoachServiceImpl(CoachRepository coachRepository, CoachMapper mapper) {
        this.coachRepository = coachRepository;
        this.mapper = mapper;
    }

    @Override
    public CoachDTO create(CoachDTO coachDTO) {

        Optional<Coach> existCoach = coachRepository.findByUsername(coachDTO.username());

        if(existCoach.isPresent()){
            throw new EntityExistsException("Coach with");
        }

        coachRepository.save(mapper.toEntity(coachDTO));

        return coachDTO;
    }

    @Override
    public CoachDTO getById(Long id) {

        if(id == null){
            throw new IllegalArgumentException("Id cant be null");
        }

        Optional<Coach> coach = coachRepository.findById(id);

        if(coach.isEmpty()){
            throw new EntityNotFoundException("Coach not Found");
        }

        return mapper.toDto(coach.get());
    }

    @Override
    public List<CoachDTO> getAll() {

        return coachRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CoachDTO update(CoachDTO coachDTO, Long id) {

        if(id == null){
            throw new IllegalArgumentException("Id cant be null");
        }

        if(!coachRepository.existsById(id)){
            throw new EntityNotFoundException("Coach not Found");
        }

        Coach updatedCoach = mapper.toEntity(coachDTO);
        updatedCoach.setId(id);

        coachRepository.save(updatedCoach);

        return coachDTO;
    }

    @Override
    public void deleteById(Long id) {

        if(id == null){
            throw new IllegalArgumentException("Id cant be null");
        }

        coachRepository.deleteById(id);
    }
}
