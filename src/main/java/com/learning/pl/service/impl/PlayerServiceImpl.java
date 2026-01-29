package com.learning.pl.service.impl;

import com.learning.pl.domain.model.Player;
import com.learning.pl.repository.PlayerDao;
import com.learning.pl.service.PlayerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerDao playerDao;

    public PlayerServiceImpl(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    @Override
    public Optional<Player> getPlayerById(Integer id) {
        return playerDao.findById(id);
    }

    @Override
    public List<Player> getPlayersBy(String name, String position, String team, String nation) {
        if (name == null || name.isBlank()) name = null;
        if (position == null || position.isBlank()) position = null;
        if (team == null || team.isBlank()) team = null;
        if (nation == null || nation.isBlank()) nation = null;
        return playerDao.findPlayersBy(name, position, team, nation);
    }

    @Override
    public List<String> getAllTeams() {
        return playerDao.findAllTeams();
    }

    @Override
    public List<String> getAllNations() {
        List<String> allNations = playerDao.findAllNations();
        List<String> nations = new ArrayList<>();
        for (String nation : allNations) {
            if (nation != null) {
                String[] s = nation.split(" ");
                nations.add(s[1]);
            }
        }
        Collections.sort(nations);
        return nations;
    }

    @Override
    public List<String> getAllPositions() {
        return playerDao.findAllPositions();
    }

}
