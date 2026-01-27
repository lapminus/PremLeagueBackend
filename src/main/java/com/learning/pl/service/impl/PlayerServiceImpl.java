package com.learning.pl.service.impl;

import com.learning.pl.domain.model.Player;
import com.learning.pl.repository.PlayerDao;
import com.learning.pl.service.PlayerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerDao playerDao;

    public PlayerServiceImpl(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    @Override
    public List<Player> getAllPlayers() {
        return playerDao.findAll();
    }
}
