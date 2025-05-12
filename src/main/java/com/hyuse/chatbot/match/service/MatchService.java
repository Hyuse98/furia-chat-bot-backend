package com.hyuse.chatbot.match.service;

import com.hyuse.chatbot.core.util.CrudService;
import com.hyuse.chatbot.match.model.MatchStatus;
import com.hyuse.chatbot.match.model.dto.MatchDTO;
import com.hyuse.chatbot.match.model.Match;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface MatchService extends CrudService<MatchDTO, Long> {

    MatchDTO getByDate(LocalDateTime date);
    List<MatchDTO> getByTeam(String teamName);
    List<MatchDTO> getByStatus(MatchStatus matchStatus);

}
