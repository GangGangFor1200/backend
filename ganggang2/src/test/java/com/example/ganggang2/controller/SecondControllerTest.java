package com.example.ganggang2.controller;

import com.example.ganggang2.domain.Category;
import com.example.ganggang2.service.CategoryService;
import com.example.ganggang2.service.PlaceService;
import com.example.ganggang2.service.StationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SecondControllerTest {

    @PersistenceContext EntityManager em;
    @Autowired
    PlaceService placeService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    StationService stationService;

    @Test
    public void 확인(){

    }
    @Test
    public void 카테고리저장(){
        //given
        Category category=new Category();
        category.setName("CAFE");
        //when

        //then

    }
    @Test
    public void 스테이션저장(){
        //given

        //when

        //then
    }
    @Test
    public void 플레이스저장(){
        //given

        //when

        //then
    }

}