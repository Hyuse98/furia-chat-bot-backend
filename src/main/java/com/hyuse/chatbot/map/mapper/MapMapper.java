package com.hyuse.chatbot.map.mapper;

import com.hyuse.chatbot.map.model.Map;
import com.hyuse.chatbot.map.model.dto.MapDTO;
import org.springframework.stereotype.Component;

@Component
public class MapMapper {

    public Map toEntity(MapDTO mapDTO) {

        return new Map(
                mapDTO.mapName(),
                mapDTO.teamAMapScore(),
                mapDTO.teamBMapScore(),
                mapDTO.matches()
        );

    }

    public MapDTO toDto(Map map) {

        return new MapDTO(
                map.getMapName(),
                map.getTeamAMapScore(),
                map.getTeamBMapScore(),
                map.getMatches()
        );
    }

}
