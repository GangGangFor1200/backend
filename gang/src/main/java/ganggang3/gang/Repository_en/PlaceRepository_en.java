package ganggang3.gang.Repository_en;
import ganggang3.gang.domain.Category;
import ganggang3.gang.domain.City;
import ganggang3.gang.domain.Place;
import ganggang3.gang.domain_en.CategoryEn;
import ganggang3.gang.domain_en.CityEn;
import ganggang3.gang.domain_en.PlaceEn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaceRepository_en extends JpaRepository<PlaceEn,Long> {

    Optional<PlaceEn> findById(Long id);
    List<PlaceEn>  findByCityAndCategory(CityEn city, CategoryEn category);

}
