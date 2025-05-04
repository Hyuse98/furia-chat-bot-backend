package com.hyuse.chatbot.team.repository;

import com.hyuse.chatbot.team.model.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    Optional<Team> findByTeamName(String name);
    Optional<Team> findByTeamTag(String tag);

    List<Team> findByTeamCountry(String country);
}
