package com.example.ganggang2.repository;

import com.example.ganggang2.domain.Category;
import com.example.ganggang2.domain.Place_Vlog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class Place_VlogRepository {

    private final EntityManager em;

    public void save (Place_Vlog place_vlog){
        em.persist(place_vlog);
    }

    public Place_Vlog findOne(Long id){
        return em.find(Place_Vlog.class,id);
    }
}
