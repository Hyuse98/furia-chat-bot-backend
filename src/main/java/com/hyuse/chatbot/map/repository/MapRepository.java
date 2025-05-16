package com.hyuse.chatbot.map.repository;

import com.hyuse.chatbot.map.model.Map;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MapRepository extends JpaRepository<Map, Long> {

    Optional<Map> findByMapName(String mapName);
}
