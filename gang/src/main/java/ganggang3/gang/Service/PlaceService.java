package ganggang3.gang.Service;

import ganggang3.gang.Repository.PlaceRepository;
import ganggang3.gang.domain.Place;
import ganggang3.gang.domain.Place_Vlog;
import ganggang3.gang.domain.Vlog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    public List<Place_Vlog> findPlaceVlogList(long placeId) {
        return placeRepository.findPlaceVlogList(placeId);
    }

    public long find(long placeId) {
        return placeRepository.findOne(placeId).getId();
    }

    public List<Place> findPlaceList(long cityId, long categoryId) {
        return placeRepository.findPlace(cityId,categoryId);
    }

    public List<Place> findTOP5(long cityId, long categoryId) {
        List<Place> placeList=placeRepository.findPlace(cityId,categoryId);
        Collections.sort(placeList, new Comparator<Place>() {
            @Override
            public int compare(Place o1, Place o2) {
                return Long.compare(o2.getPlace_vlogList().size(),o1.getPlace_vlogList().size());
            }
        });
        return placeList.subList(0,5);
    }

    public List<Vlog> findVlogList(long placeId) {
        List<Place_Vlog> placeVlogList=findPlaceVlogList(placeId);
        List<Vlog> vlogList=new ArrayList<>();
        for(int i=0;i<placeVlogList.size();i++){
            Place_Vlog placeVlog=placeVlogList.get(i);
            vlogList.add(placeVlog.getVlog());
        }
        return vlogList;
    }
}
