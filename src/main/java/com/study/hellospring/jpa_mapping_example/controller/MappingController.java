package com.study.hellospring.jpa_mapping_example.controller;

import com.study.hellospring.jpa_mapping_example.domain.dto.*;
import com.study.hellospring.jpa_mapping_example.domain.entity.*;
import com.study.hellospring.jpa_mapping_example.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * N:M 관계 예제
     */
    private final ExamineeRepository examineeRepository;
    private final AcademyRepository academyRepository;
    private final ExamineeAcademyRepository examineeAcademyRepository;

    @PostMapping("/register-academy")
    public String registerAcademy(@RequestBody RegisterRequest request) {
        Examinee examinee = examineeRepository.findById(request.getExamineeId()).get();
        Academy academy = academyRepository.findById(request.getAcademyId()).get();

        ExamineeAcademy newRegister = new ExamineeAcademy();
        newRegister.setExaminee(examinee);
        newRegister.setAcademy(academy);
        newRegister.setRegisterDate(LocalDateTime.now());
        examineeAcademyRepository.save(newRegister);

        return String.format("%s 수험생이 %s에 등록하였습니다.", examinee.getName(), academy.getName());
    }

    @GetMapping("/register-academy")
    public String showRegisterAcademy() {
        List<ExamineeAcademy> examineeAcademies = examineeAcademyRepository.findAll();

        List<ExamineeAcademyDto> dto = new ArrayList<>();
        for(ExamineeAcademy examineeAcademy : examineeAcademies) {
            dto.add(ExamineeAcademyDto.of(examineeAcademy));
        }

        return dto.toString();
    }

    @GetMapping("/academy/{academyId}")
    public String showAcademy(@PathVariable Long academyId) {
        Academy academy = academyRepository.findById(academyId).get();
        return AcademyDto.of(academy).toString();
    }

    @GetMapping("/examinee/{examineeId}")
    public String showExaminee(@PathVariable Long examineeId) {
        Examinee examinee = examineeRepository.findById(examineeId).get();
        return ExamineeDto.of(examinee).toString();
    }

}
