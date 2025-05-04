package com.hyuse.chatbot.match.service.impl;

import com.hyuse.chatbot.match.model.dto.MatchDTO;
import com.hyuse.chatbot.match.model.Match;
import com.hyuse.chatbot.match.model.MatchStatus;
import com.hyuse.chatbot.match.repository.MatchRepository;
import com.hyuse.chatbot.match.service.MatchServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MatchServiceImpl implements MatchServiceInterface {

    private final MatchRepository matchRepository;

    @Autowired
    public MatchServiceImpl(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public Flux<Match> findNextMatch() {
        return Flux.fromIterable(matchRepository.findTop3ByMatchStatusOrderByDateHourAsc(MatchStatus.SCHEDULED));
    }

    public Mono<Match> findLastMatch() {
        return Mono.justOrEmpty(matchRepository.findTop1ByMatchStatusOrderByDateHourDesc(MatchStatus.CLOSED));
    }

    public Mono<Match> findActualMatch() {
        return Mono.justOrEmpty(matchRepository.findTop1ByMatchStatusOrderByDateHourDesc(MatchStatus.LIVE));
    }

    @Override
    public void createMatch(MatchDTO matchDTO) {
        Match newMatch = new Match(
                matchDTO.matchScoreboard(),
                matchDTO.matchResult(),
                matchDTO.matchStatus(),
                matchDTO.dateHour(),
                matchDTO.teamA(),
                matchDTO.teamB()
        );
        matchRepository.save(newMatch);
    }

    @Override
    public void updateMatchById(Long id, MatchDTO matchDTO) {
        var updatedMatch = matchRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Partida não encontrada com o ID: " + id));
        updatedMatch.setId(id);
        matchRepository.save(updatedMatch);
    }

    @Override
    public Match getMatchById(Long id) {
        return matchRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Partida não encontrada com o ID: " + id));
    }

    @Override
    public Match getMatchByDate(LocalDateTime date) {
        return matchRepository.findByDateHour(date)
                .orElseThrow(() -> new NoSuchElementException("Partida não encontrada na data: " + date));
    }

    @Override
    public List<Match> findMatchesByTeam(String teamName){
        return matchRepository.findAll();
    }

    @Override
    public List<Match> listByMatchStatus(MatchStatus matchStatus) {
        return matchRepository.findAllByMatchStatus(matchStatus);
    }

    public Page<Match> listMatchs(Pageable pageable) {
        return matchRepository.findAll(pageable);
    }

    @Override
    public void deleteMatchById(Long id) {
        if (!matchRepository.existsById(id)) {
            throw new NoSuchElementException("Partida não encontrada com o ID: " + id);
        }
        matchRepository.deleteById(id);
    }

}
