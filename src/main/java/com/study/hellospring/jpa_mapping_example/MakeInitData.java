package com.study.hellospring.jpa_mapping_example;

import com.study.hellospring.jpa_mapping_example.domain.entity.Academy;
import com.study.hellospring.jpa_mapping_example.domain.entity.Customer;
import com.study.hellospring.jpa_mapping_example.domain.entity.Seat;
import com.study.hellospring.jpa_mapping_example.domain.entity.Examinee;
import com.study.hellospring.jpa_mapping_example.repository.AcademyRepository;
import com.study.hellospring.jpa_mapping_example.repository.CustomerRepository;
import com.study.hellospring.jpa_mapping_example.repository.ExamineeRepository;
import com.study.hellospring.jpa_mapping_example.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class MakeInitData {
    private final CustomerRepository customerRepository;
    private final SeatRepository seatRepository;
    private final ExamineeRepository examineeRepository;
    private final AcademyRepository academyRepository;

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

    @PostConstruct
    public void makeStudentAndAcademy() {
        Examinee examinee1 = new Examinee();
        examinee1.setName("Tom");
        examinee1.setAge(18);
        examineeRepository.save(examinee1);

        Examinee examinee2 = new Examinee();
        examinee2.setName("Alice");
        examinee2.setAge(17);
        examineeRepository.save(examinee2);

        Examinee examinee3 = new Examinee();
        examinee3.setName("Harry");
        examinee3.setAge(17);
        examineeRepository.save(examinee3);

        Academy academy1 = new Academy();
        academy1.setName("수학학원");
        academyRepository.save(academy1);

        Academy academy2 = new Academy();
        academy2.setName("영어학원");
        academyRepository.save(academy2);

        Academy academy3 = new Academy();
        academy3.setName("국어학원");
        academyRepository.save(academy3);
    }
}
