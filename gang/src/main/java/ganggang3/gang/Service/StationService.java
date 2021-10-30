package ganggang3.gang.Service;

import ganggang3.gang.Repository.CityRepository;
import ganggang3.gang.Repository.StationRepositoty;
import ganggang3.gang.domain.City;
import ganggang3.gang.domain.Station;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StationService {
    private final StationRepositoty stationRepositoty;
    private final CityRepository cityRepository;
    public List<Station> findAll(){
        List<Station> all = stationRepositoty.findAll();
        return all;
    }

    public List<Station> findAllByCity(Long city_id){
        Optional<City> city = cityRepository.findById(city_id);
        List<Station> findByCity = stationRepositoty.findAllByCity(city.get());
        return findByCity;

    }
}
