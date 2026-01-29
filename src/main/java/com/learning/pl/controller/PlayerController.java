package com.learning.pl.controller;

import com.learning.pl.domain.model.Player;
import com.learning.pl.service.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/players")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Integer id) {
        Optional<Player> player = playerService.getPlayerById(id);
        return player.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Player>> getPlayersBy(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String position,
            @RequestParam(required = false) String team,
            @RequestParam(required = false) String nation) {
        List<Player> results = playerService.getPlayersBy(name, position, team, nation);
        return ResponseEntity.ok(results);
    }

    @GetMapping(path = "/teams")
    public ResponseEntity<List<String>> getAllTeams() {
        List<String> teams = playerService.getAllTeams();
        return ResponseEntity.ok(teams);
    }

    @GetMapping(path = "/nations")
    public ResponseEntity<List<String>> getAllNations() {
        List<String> nations = playerService.getAllNations();
        return ResponseEntity.ok(nations);
    }

    @GetMapping(path = "/positions")
    public ResponseEntity<List<String>> getAllPositions() {
        List<String> positions = playerService.getAllPositions();
        return ResponseEntity.ok(positions);
    }

}
