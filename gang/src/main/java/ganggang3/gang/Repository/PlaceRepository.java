package ganggang3.gang.Repository;

import ganggang3.gang.domain.Place;
import ganggang3.gang.domain.Place_Vlog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PlaceRepository {

    private final EntityManager em;

    public List<Place> findName(String name){
        return em.createQuery("select m from Place m where m.name = :name",Place.class)
                .setParameter("name",name)
                .getResultList();
    }

    public Place findOne(Long id){
        return em.find(Place.class,id);
    }

    public List<Place> findPlace(long cityId, long categoryId) {
        return em.createQuery("select m  from Place m where m.category.id= :categoryid and m.city.id= :cityid",Place.class)
                .setParameter("categoryid",categoryId)
                .setParameter("cityid",cityId)
                .getResultList();
    }

    public List<Place_Vlog> findPlaceVlogList(long placeId) {
        Place place=findOne(placeId);
        return place.getPlace_vlogList();
    }
}
