package com.example.ganggang2.repository;

import com.example.ganggang2.domain.Category;
import com.example.ganggang2.domain.Vlog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class VlogRepository {

    private final EntityManager em;

    public void save(Vlog vlog) {
        em.persist(vlog);
    }

    public Vlog findOne(Long id){
        return em.find(Vlog.class,id);
    }
}
