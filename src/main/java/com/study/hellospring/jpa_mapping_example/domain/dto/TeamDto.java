package com.study.hellospring.jpa_mapping_example.domain.dto;

import com.study.hellospring.jpa_mapping_example.domain.entity.Player;
import com.study.hellospring.jpa_mapping_example.domain.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Builder
@ToString
public class TeamDto {
    private Long id;
    private String name;
    private List<String> playersName;

    public static TeamDto of(Team team) {
        return TeamDto.builder()
                .id(team.getId())
                .name(team.getName())
                .playersName(team.getPlayers().stream().map(list -> {
                    return list.getName();
                }).collect(Collectors.toList()))
                .build();
    }
}
