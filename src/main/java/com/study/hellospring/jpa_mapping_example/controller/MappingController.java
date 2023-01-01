package com.study.hellospring.jpa_mapping_example.controller;

import com.study.hellospring.jpa_mapping_example.domain.dto.*;
import com.study.hellospring.jpa_mapping_example.domain.entity.Customer;
import com.study.hellospring.jpa_mapping_example.domain.entity.Player;
import com.study.hellospring.jpa_mapping_example.domain.entity.Seat;
import com.study.hellospring.jpa_mapping_example.domain.entity.Team;
import com.study.hellospring.jpa_mapping_example.repository.CustomerRepository;
import com.study.hellospring.jpa_mapping_example.repository.PlayerRepository;
import com.study.hellospring.jpa_mapping_example.repository.SeatRepository;
import com.study.hellospring.jpa_mapping_example.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jpa-mapping-example")
@RequiredArgsConstructor
public class MappingController {

    /**
     * 1:N 관계 예제
     */
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

    /**
     * 1:1 관계 예제
     */
    private final CustomerRepository customerRepository;
    private final SeatRepository seatRepository;
    @PostMapping("/reservation")
    public String reservation(@RequestBody ReservationRequest request) {
        Customer customer = customerRepository.findById(request.getCustomerId()).get();
        Seat seat = seatRepository.findById(request.getSeatId()).get();
        customer.setSeat(seat);
        customerRepository.save(customer);
        return String.format("%s 손님 (%s, %d) 좌석 예약 성공", customer.getName(), seat.getRowId(), seat.getColId());
    }

    @GetMapping("/reservation-check")
    public String reservationCheck(@RequestBody ReservationCheckRequest request) {
        Seat seat = seatRepository.findByRowIdAndColId(request.getRowId(), request.getColId()).get();
        if(seat.getCustomer() == null) {
            return String.format("(%s, %d) 좌석은 예약되지 않았습니다.", request.getRowId(), request.getColId());
        } else {
            return String.format("(%s, %d) 좌석 예약 손님 : %s", request.getRowId(), request.getColId(), seat.getCustomer().getName());
        }
    }
}
