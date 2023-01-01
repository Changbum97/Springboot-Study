package com.study.hellospring.jpa_mapping_example.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReservationRequest {
    private Long customerId;
    private Long seatId;
}
