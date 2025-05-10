package com.hyuse.chatbot.player.service.impl;

import com.hyuse.chatbot.player.mapper.PlayerMapper;
import com.hyuse.chatbot.player.model.Player;
import com.hyuse.chatbot.player.model.dto.PlayerDTO;
import com.hyuse.chatbot.player.repository.PlayerRepository;
import com.hyuse.chatbot.player.service.PlayerService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerMapper mapper;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository, PlayerMapper mapper) {
        this.playerRepository = playerRepository;
        this.mapper = mapper;
    }

    @Override
    public PlayerDTO create(PlayerDTO playerDTO) {

        Optional<Player> existPlayer = playerRepository.findByUsername(playerDTO.username());

        if (existPlayer.isPresent()) {
            throw new EntityExistsException("Player with username" + existPlayer.get().getUsername() + " alredy exist");
        }

        Player player = mapper.toEntity(playerDTO);

        playerRepository.save(player);

        return playerDTO;
    }

    @Override
    public PlayerDTO getById(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("Id null");
        }

        Optional<Player> player = playerRepository.findById(id);

        if (player.isEmpty()) {
            throw new EntityNotFoundException("Player Not Found");
        }

        return mapper.toDto(player.get());
    }

    @Override
    public List<PlayerDTO> getAll() {

        return playerRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PlayerDTO update(PlayerDTO playerDTO, Long id) {

        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }

        if (!playerRepository.existsById(id)) {
            throw new EntityNotFoundException("Player not Found");
        }

        Player playerToUpadate = mapper.toEntity(playerDTO);
        playerToUpadate.setId(id);

        Player updatedPlayer = playerRepository.save(playerToUpadate);
        return mapper.toDto(updatedPlayer);
    }

    @Override
    public void deleteById(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("id null");
        }

        if (!playerRepository.existsById(id)) {
            throw new EntityNotFoundException("Player not Found");
        }

        playerRepository.deleteById(id);
    }

    @Override
    public PlayerDTO getByUsername(String username) {

        if(username.isBlank()){
            throw new IllegalArgumentException("Username cant be Blank");
        }

        Player player = playerRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("Jogador não encontrado com o nome: " + username));

        return mapper.toDto(player);
    }

    @Override
    public PlayerDTO getByName(String name) {

        if(name.isBlank()){
            throw new IllegalArgumentException("Name cant be Blank");
        }

        Player player = playerRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException("Jogador não encontrado com o nome: " + name));

        return mapper.toDto(player);
    }

    @Override
    public List<PlayerDTO> getByTeam(String team) {

        if(team.isBlank()){
            throw new IllegalArgumentException("Team cant be Blank");
        }

        List<Player> players = playerRepository.findByTeam(team);

        if(players.isEmpty()){
            throw new EntityNotFoundException("No players Registered");
        }

        return players
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

}
