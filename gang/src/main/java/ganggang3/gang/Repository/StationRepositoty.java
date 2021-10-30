package ganggang3.gang.Repository;

import ganggang3.gang.domain.City;
import ganggang3.gang.domain.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationRepositoty extends JpaRepository<Station, Long> {
    List<Station> findAllByCity(City city);
}
