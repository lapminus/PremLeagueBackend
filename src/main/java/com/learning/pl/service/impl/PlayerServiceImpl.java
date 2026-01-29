package com.learning.pl.service.impl;

import com.learning.pl.domain.model.Player;
import com.learning.pl.repository.PlayerDao;
import com.learning.pl.service.PlayerService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
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

    @Override
    public Player createPlayer(Player player) {
        return playerDao.save(player);
    }

    @Transactional
    @Override
    public Player updatePlayer(Integer id, Player player) {
        Player existingPlayer = playerDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Player not found."));

        copyNonNullFields(player, existingPlayer);

        return playerDao.save(existingPlayer);
    }

    private void copyNonNullFields(Player source, Player target) {
        for (Field field : source.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(source);
                if (value != null) {
                    field.set(target, value);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void deletePlayer(Integer id) {
        playerDao.deleteById(id);
    }


}
