package ganggang3.gang.Repository_en;
import ganggang3.gang.domain.City;
import ganggang3.gang.domain_en.CityEn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository_en extends JpaRepository<CityEn,Long> {

    Optional<CityEn> findById(Long id);

}
