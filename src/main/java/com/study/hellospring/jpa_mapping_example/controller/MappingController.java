package com.study.hellospring.jpa_mapping_example.controller;

import com.study.hellospring.jpa_mapping_example.domain.dto.PlayerAddRequest;
import com.study.hellospring.jpa_mapping_example.domain.dto.PlayerDto;
import com.study.hellospring.jpa_mapping_example.domain.dto.TeamDto;
import com.study.hellospring.jpa_mapping_example.domain.entity.Player;
import com.study.hellospring.jpa_mapping_example.domain.entity.Team;
import com.study.hellospring.jpa_mapping_example.repository.PlayerRepository;
import com.study.hellospring.jpa_mapping_example.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jpa-mapping-example")
@RequiredArgsConstructor
public class MappingController {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    @PostMapping("/player")
    public String addPlayer(@RequestBody PlayerAddRequest request) {
        Player player = new Player();
        player.setName(request.getName());
        player.setAge(request.getAge());
        player.setTeam(teamRepository.findById(request.getTeamId()).get());
        playerRepository.save(player);
        return "Player 추가 성공";
    }

    @PostMapping("/team")
    public String addTeam(@RequestBody String teamName) {
        Team newTeam = new Team();
        newTeam.setName(teamName);
        teamRepository.save(newTeam);
        return "Team 추가 성공";
    }

    @GetMapping("/player/{playerId}")
    public String showPlayer(@PathVariable Long playerId) {
        Player player = playerRepository.findById(playerId).get();
        PlayerDto playerDto = PlayerDto.of(player);
        return playerDto.toString();
    }

    @GetMapping("/team/{teamId}")
    public String showTeam(@PathVariable Long teamId) {
        Team team = teamRepository.findById(teamId).get();
        TeamDto teamDto = TeamDto.of(team);
        return teamDto.toString();
    }
}
