package com.example.ganggang2.repository;

import com.example.ganggang2.domain.Category;
import com.example.ganggang2.domain.Station;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class StationRepository {

    private final EntityManager em;

    public void save(Station station){
        em.persist(station);
    }

    public Station findOne(Long id){
        return em.find(Station.class,id);
    }
}
