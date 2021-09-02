package com.example.ganggang2.repository;

import com.example.ganggang2.domain.Category;
import com.example.ganggang2.domain.Place;
import com.example.ganggang2.domain.Vlog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PlaceRepository {

    private final EntityManager em;

    public void save(Place place){
        em.persist(place);
    }

    public Place findOne(Long id){
        return em.find(Place.class,id);
    }

    public List<Place> findTOP5(Long stationId,Long categoryId){
        //place중 mapping으로 얻은 stationId와 station의 Id가 같고(name으로 해도 됨)
        //category id와 category Id가 같고 rank가 0보다 큰 리스트를 가져와서 뿌려야함
        return em.createQuery("select i from Place i where i.station.id="+stationId+" and i.category.id="+categoryId+" and i.rank>0",Place.class)
                .getResultList();
    }

}
