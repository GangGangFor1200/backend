package ganggang3.gang.Repository;

import ganggang3.gang.domain.CategoryEn;
import ganggang3.gang.domain.City;
import ganggang3.gang.domain.PlaceEn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<PlaceEn,Long> {

    Optional<PlaceEn> findById(Long id);
    List<PlaceEn>  findByCityAndCategory(City city, CategoryEn category);

}
