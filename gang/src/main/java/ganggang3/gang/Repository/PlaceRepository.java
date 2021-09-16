package ganggang3.gang.Repository;

import ganggang3.gang.domain.Category;
import ganggang3.gang.domain.City;
import ganggang3.gang.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<Place,Long> {

    Optional<Place> findById(long id);
    List<Place>  findByCityAndCategory(City city, Category category);

}
