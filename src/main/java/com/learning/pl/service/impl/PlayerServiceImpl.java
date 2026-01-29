package com.learning.pl.service.impl;

import com.learning.pl.domain.model.Player;
import com.learning.pl.repository.PlayerDao;
import com.learning.pl.service.PlayerService;
import jakarta.transaction.Transactional;
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

    @Override
    public Player createPlayer(Player player) {
        return playerDao.save(new Player(
                null,
                player.getPlayerName(),
                player.getNation(),
                player.getPos(),
                player.getAge(),
                player.getMatchesPlayed(),
                player.getStarts(),
                player.getMinutesPlayed(),
                player.getGoals(),
                player.getAssists(),
                player.getPenaltiesScored(),
                player.getYellowCards(),
                player.getRedCards(),
                player.getExpectedGoals(),
                player.getExpectedAssists(),
                player.getTeamName()));
    }

    @Transactional
    @Override
    public Player updatePlayer(Integer id, Player player) {
        Player existingPlayer = playerDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Player not found."));

        existingPlayer.setPlayerName(player.getPlayerName() != null ? player.getPlayerName() : existingPlayer.getPlayerName());
        existingPlayer.setNation(player.getNation() != null ? player.getNation() : existingPlayer.getNation());
        existingPlayer.setPos(player.getPos() != null ? player.getPos() : existingPlayer.getPos());
        existingPlayer.setAge(player.getAge() != null ? player.getAge() : existingPlayer.getAge());
        existingPlayer.setMatchesPlayed(player.getMatchesPlayed() != null ? player.getMatchesPlayed() : existingPlayer.getMatchesPlayed());
        existingPlayer.setStarts(player.getStarts() != null ? player.getStarts() : existingPlayer.getStarts());
        existingPlayer.setMinutesPlayed(player.getMinutesPlayed() != null ? player.getMinutesPlayed() : existingPlayer.getMinutesPlayed());
        existingPlayer.setGoals(player.getGoals() != null ? player.getGoals() : existingPlayer.getGoals());
        existingPlayer.setAssists(player.getAssists() != null ? player.getAssists() : existingPlayer.getAssists());
        existingPlayer.setPenaltiesScored(player.getPenaltiesScored() != null ? player.getPenaltiesScored() : existingPlayer.getPenaltiesScored());
        existingPlayer.setYellowCards(player.getYellowCards() != null ? player.getYellowCards() : existingPlayer.getYellowCards());
        existingPlayer.setRedCards(player.getRedCards() != null ? player.getRedCards() : existingPlayer.getRedCards());
        existingPlayer.setExpectedGoals(player.getExpectedGoals() != null ? player.getExpectedGoals() : existingPlayer.getExpectedGoals());
        existingPlayer.setExpectedAssists(player.getExpectedAssists() != null ? player.getExpectedAssists() : existingPlayer.getExpectedAssists());
        existingPlayer.setTeamName(player.getTeamName() != null ? player.getTeamName() : existingPlayer.getTeamName());

        return playerDao.save(existingPlayer);
    }

    @Override
    public void deletePlayer(Integer id) {
        playerDao.deleteById(id);
    }


}
