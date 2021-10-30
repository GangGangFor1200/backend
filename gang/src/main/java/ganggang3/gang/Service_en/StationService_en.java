package ganggang3.gang.Service;

import ganggang3.gang.Repository.CityRepository;
import ganggang3.gang.Repository.StationRepositoty;
import ganggang3.gang.Repository_en.CityRepository_en;
import ganggang3.gang.Repository_en.StationRepository_en;
import ganggang3.gang.domain.City;
import ganggang3.gang.domain.Station;
import ganggang3.gang.domain_en.CityEn;
import ganggang3.gang.domain_en.StationEn;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StationService_en {
    private final StationRepository_en stationRepositoty;
    private final CityRepository_en cityRepository;

    public List<StationEn> findAll(){
        List<StationEn> all = stationRepositoty.findAll();
        return all;
    }

    public List<StationEn> findAllByCity(Long city_id){
        Optional<CityEn> city = cityRepository.findById(city_id);
        List<StationEn> findByCity = stationRepositoty.findAllByCity(city.get());
        return findByCity;
    }

}
