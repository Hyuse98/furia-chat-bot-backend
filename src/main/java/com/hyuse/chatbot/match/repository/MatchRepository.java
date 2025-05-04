package com.hyuse.chatbot.match.repository;

import com.hyuse.chatbot.match.model.Match;
import com.hyuse.chatbot.match.model.MatchStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    List<Match> findTop3ByMatchStatusOrderByDateHourAsc(MatchStatus matchStatus);

    Optional<Match> findTop1ByMatchStatusOrderByDateHourDesc(MatchStatus matchStatus);

    Optional<Match> findTop1ByDateHourBetween(LocalDateTime start, LocalDateTime end);

    Optional<Match> findByDateHour(LocalDateTime date);

    List<Match> findAllByMatchStatus(MatchStatus matchStatus);
}

