package com.hyuse.chatbot.match.service.impl;

import com.hyuse.chatbot.match.mapper.MatchMapper;
import com.hyuse.chatbot.match.model.dto.MatchDTO;
import com.hyuse.chatbot.match.model.Match;
import com.hyuse.chatbot.match.model.MatchStatus;
import com.hyuse.chatbot.match.repository.MatchRepository;
import com.hyuse.chatbot.match.service.MatchService;
import com.hyuse.chatbot.player.model.Player;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;
    private final MatchMapper mapper;

    @Autowired
    public MatchServiceImpl(MatchRepository matchRepository, MatchMapper mapper) {
        this.matchRepository = matchRepository;
        this.mapper = mapper;
    }

    @Override
    public MatchDTO create(MatchDTO matchDTO) {

        //TODO Fazer uma verificaçõo se a partida existe comparadando Team A, Team B e Data

//        Optional<Match> existMatch ???
//        if(existMatch.isPresent()){
//            throw new EntityExistsException("Match already exist");
//        }

        Match match = mapper.toEntity(matchDTO);

        matchRepository.save(match);

        return matchDTO;
    }

    @Override
    public MatchDTO getById(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("Id null");
        }

        Optional<Match> match = matchRepository.findById(id);

        if(match.isEmpty()){
            throw new EntityNotFoundException("Match not found");
        }

        return mapper.toDto(match.get());
    }

    @Override
    public List<MatchDTO> getAll() {

        return matchRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MatchDTO update(MatchDTO matchDTO, Long id) {

        if (id == null) {
            throw new IllegalArgumentException("Id null");
        }

        if (!matchRepository.existsById(id)){
            throw new EntityNotFoundException("Match not Found");
        }

        Match matchToUpdate = mapper.toEntity(matchDTO);
        matchToUpdate.setId(id);

        Match updatedMatch = matchRepository.save(matchToUpdate);
        return mapper.toDto(updatedMatch);
    }

    @Override
    public void deleteById(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("Id null");
        }

        if (!matchRepository.existsById(id)){
            throw new EntityNotFoundException("Match not found");
        }

        matchRepository.deleteById(id);
    }

    @Override
    public MatchDTO getByDate(LocalDateTime date) {

        if(date == null){
            throw new IllegalArgumentException("Date cant be null");
        }

        var match = matchRepository.findByMatchDate(date);

        if(match.isEmpty()){
            throw new EntityNotFoundException("No Matches on list");
        }

        return mapper.toDto(match.get());
    }

    @Override
    public List<MatchDTO> getByTeam(String teamName) {

        if(teamName.isBlank()){
            throw new IllegalArgumentException("Team name cant be Blank");
        }

        return matchRepository.findAllByTeamName(teamName)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MatchDTO> getByStatus(MatchStatus matchStatus) {

        if(matchStatus.describeConstable().isEmpty()){
            throw new IllegalArgumentException("Status cant be Blank");
        }

        return matchRepository.findAllByMatchStatus(matchStatus)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }


//    public Flux<Match> findNextMatch() {
//        return Flux.fromIterable(matchRepository.findTop3ByMatchStatusOrderByDateHourAsc(MatchStatus.SCHEDULED));
//    }
//
//    public Mono<Match> findLastMatch() {
//        return Mono.justOrEmpty(matchRepository.findTop1ByMatchStatusOrderByDateHourDesc(MatchStatus.CLOSED));
//    }
//
//    public Mono<Match> findActualMatch() {
//        return Mono.justOrEmpty(matchRepository.findTop1ByMatchStatusOrderByDateHourDesc(MatchStatus.LIVE));
//    }

}
