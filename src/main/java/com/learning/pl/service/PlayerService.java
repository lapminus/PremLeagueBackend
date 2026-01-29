package com.learning.pl.service;

import com.learning.pl.domain.model.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerService {
    Optional<Player> getPlayerById(Integer id);
    List<Player> getPlayersBy(String name, String position, String team, String nation);

    List<String> getAllTeams();
    List<String> getAllNations();
    List<String> getAllPositions();
}