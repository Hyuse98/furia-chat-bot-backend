package com.hyuse.chatbot.coach.mapper;

import com.hyuse.chatbot.coach.model.Coach;
import com.hyuse.chatbot.coach.model.dto.CoachDTO;
import org.springframework.stereotype.Component;

@Component
public class CoachMapper {

    public Coach toEntity(CoachDTO coachDTO){

            return new Coach(
                    coachDTO.name(),
                    coachDTO.username(),
                    coachDTO.team(),
                    coachDTO.hltvUrl()
            );
    }

    public CoachDTO toDto(Coach coach){

        return new CoachDTO(
                coach.getName(),
                coach.getUsername(),
                coach.getTeam(),
                coach.getHltvUrl()
        );
    }
}
