package com.example.ganggang2.repository;

import com.example.ganggang2.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class CategoryRepository {

    private final EntityManager em;

    public void save (Category category){
        em.persist(category);
    }

    public Category findOne(Long id){
        return em.find(Category.class,id);
    }
}
