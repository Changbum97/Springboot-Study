package com.study.hellospring.jpa_mapping_example;

import com.study.hellospring.jpa_mapping_example.domain.entity.Customer;
import com.study.hellospring.jpa_mapping_example.domain.entity.Seat;
import com.study.hellospring.jpa_mapping_example.repository.CustomerRepository;
import com.study.hellospring.jpa_mapping_example.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

//@Component
@RequiredArgsConstructor
public class MakeInitData {
    private final CustomerRepository customerRepository;
    private final SeatRepository seatRepository;
    @PostConstruct
    public void makeSeatAndCustomer() {

        for(char rowId = 'A' ; rowId <= 'C' ; rowId ++) {
            for(int colId = 1 ; colId <= 5 ; colId ++) {
                Seat seat = new Seat();
                seat.setRowId(String.valueOf(rowId));
                seat.setColId(colId);
                seatRepository.save(seat);
            }
        }

        for(int i = 1 ; i <= 3 ; i ++) {
            Customer customer = new Customer();
            customer.setName("customer" + i);
            customerRepository.save(customer);
        }
    }
}
