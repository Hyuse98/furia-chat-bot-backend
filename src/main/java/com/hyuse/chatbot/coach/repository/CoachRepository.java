package com.hyuse.chatbot.coach.repository;

import com.hyuse.chatbot.coach.model.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface CoachRepository extends JpaRepository<Coach, Long> {


    Optional<Coach> findByUsername(@NonNull String username);
}
