package com.learning.pl.controller;

import com.learning.pl.domain.dto.PlayerDto;
import com.learning.pl.domain.model.Player;
import com.learning.pl.mapper.PlayerMapper;
import com.learning.pl.service.PlayerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/players")
public class PlayerController {
    private final PlayerService playerService;
    private final PlayerMapper playerMapper;

    public PlayerController(PlayerService playerService, PlayerMapper playerMapper) {
        this.playerService = playerService;
        this.playerMapper = playerMapper;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PlayerDto> getPlayerById(@PathVariable Integer id) {
        Optional<Player> player = playerService.getPlayerById(id);
        return player.map(value -> new ResponseEntity<>(playerMapper.toDto(value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<PlayerDto>> getPlayersBy(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String position,
            @RequestParam(required = false) String team,
            @RequestParam(required = false) String nation) {
        List<Player> results = playerService.getPlayersBy(name, position, team, nation);
        return ResponseEntity.ok(results.stream().map(playerMapper::toDto).toList());
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

    @PostMapping
    public ResponseEntity<PlayerDto> createPlayer(
            @Valid @RequestBody PlayerDto dto) {
        Player player = playerService.createPlayer(playerMapper.toEntity(dto));
        return new ResponseEntity<>(playerMapper.toDto(player), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<PlayerDto> updatePlayer(
            @PathVariable Integer id,
            @Valid @RequestBody PlayerDto dto) {
        Player player = playerService.updatePlayer(id, playerMapper.toEntity(dto));
        return ResponseEntity.ok(playerMapper.toDto(player));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Integer id) {
        playerService.deletePlayer(id);
        return ResponseEntity.noContent().build();
    }

}
