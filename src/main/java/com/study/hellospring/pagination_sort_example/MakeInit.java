package com.study.hellospring.pagination_sort_example;

import com.study.hellospring.pagination_sort_example.domain.Gamer;
import com.study.hellospring.pagination_sort_example.domain.Rank;
import com.study.hellospring.pagination_sort_example.repository.GamerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class MakeInit {

    private final GamerRepository gamerRepository;

    @PostConstruct
    public void init() {
        String[] nameList = new String[]{"Tom", "Harry", "Alice", "James", "Lucy", "Jane", "Jack", "Kane", "Alex", "Emily",
                "Eric", "Jessica", "Lisa", "Paul", "Victoria", "Sophia", "Melissa", "Thomas", "Peter", "Matthew",
                "Julia", "Henry", "Mary", "Michelle", "Olivia"};
        int[] ageList = new int[]{18, 20, 19, 21, 25, 17, 22, 28, 27, 20,
                16, 21, 19, 17, 23, 26, 24, 27, 22, 20,
                18, 24, 21, 20, 25};
        Rank[] rankList = new Rank[]{Rank.BRONZE, Rank.GOLD, Rank.DIAMOND, Rank.BRONZE, Rank.SILVER,
                Rank.GOLD, Rank.BRONZE, Rank.PLATINUM, Rank.SILVER, Rank.BRONZE,
                Rank.PLATINUM, Rank.SILVER, Rank.DIAMOND, Rank.GOLD, Rank.BRONZE,
                Rank.SILVER, Rank.BRONZE, Rank.GOLD, Rank.PLATINUM, Rank.PLATINUM,
                Rank.GOLD, Rank.BRONZE, Rank.DIAMOND, Rank.SILVER, Rank.SILVER};

        for(int i = 0 ; i < 25 ; i ++) {
            Gamer gamer = new Gamer();
            gamer.setName(nameList[i]);
            gamer.setAge(ageList[i]);
            gamer.setRank(rankList[i]);
            gamerRepository.save(gamer);
        }
    }
}
