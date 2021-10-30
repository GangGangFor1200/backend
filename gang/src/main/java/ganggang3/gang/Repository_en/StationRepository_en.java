package ganggang3.gang.Repository_en;

import ganggang3.gang.domain.City;
import ganggang3.gang.domain.Station;
import ganggang3.gang.domain_en.StationEn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationRepository_en extends JpaRepository<StationEn ,Long> {
    List<StationEn> findAllByCity(City city);
}
