package com.study.hellospring.jpa_mapping_example.domain.dto;

import com.study.hellospring.jpa_mapping_example.domain.entity.Player;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Builder
@ToString
public class PlayerDto {
    private Long id;
    private String name;
    private Integer age;
    private String teamName;

    public static PlayerDto of(Player player) {
        return PlayerDto.builder()
                .id(player.getId())
                .name(player.getName())
                .age(player.getAge())
                .teamName(player.getTeam().getName())
                .build();
    }
}
