package com.hyuse.chatbot.player.service.impl;

import com.hyuse.chatbot.player.model.dto.PlayerDTO;
import com.hyuse.chatbot.player.model.Player;
import com.hyuse.chatbot.player.repository.PlayerRepository;
import com.hyuse.chatbot.player.service.PlayerServiceInterface;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerServiceInterface {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public void createPlayer(PlayerDTO playerDTO) {
        Player player = new Player();
        player.setName(playerDTO.name());
        playerRepository.save(player);
    }

    //TODO Trata o erro corretamente
    @Override
    public void updatePlayer(PlayerDTO playerDTO, Long id) {
        Player existingPlayer = playerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Jogador não encontrado com o ID: " + id));

        existingPlayer.setName(playerDTO.name());
        playerRepository.save(existingPlayer);
    }

    //TODO Trata o erro corretamente
    @Override
    public Player getPlayerById(Long id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Jogador não encontrado com o ID: " + id));
    }

    //TODO Trata o erro corretamente
    @Override
    public Player getPlayerByName(String name) {
        return playerRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Jogador não encontrado com o nome: " + name));
    }

    @Override
    public Page<Player> listPlayers(Pageable pageable) {
        return playerRepository.findAll(pageable);
    }

    //TODO Trata o erro corretamente
    @Override
    public void deletePlayerById(Long id) {
        if (!playerRepository.existsById(id)) {
            throw new EntityNotFoundException("Jogador não encontrado com o ID: " + id);
        }
        playerRepository.deleteById(id);
    }

}
