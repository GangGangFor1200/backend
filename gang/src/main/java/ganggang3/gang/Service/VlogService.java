package ganggang3.gang.Service;

import ganggang3.gang.Repository.PlaceRepository;
import ganggang3.gang.Repository.VlogRepository;
import ganggang3.gang.domain.Place;
import ganggang3.gang.domain.Place_Vlog;
import ganggang3.gang.domain.Vlog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class VlogService {

    private final VlogRepository vlogRepository;

    public List<Place_Vlog> findPlaceVlogList(long vlogId) {
        return vlogRepository.findPlaceVlogList(vlogId);
    }

    public List<Place> findPlaceList(long vlogId) {
        List<Place_Vlog> placeVlogList=findPlaceVlogList(vlogId);
        List<Place> placeList=new ArrayList<>();
        for(int i=0;i<placeVlogList.size();i++){
            Place_Vlog placeVlog=placeVlogList.get(i);
            placeList.add(placeVlog.getPlace());
        }
        return placeList;
    }
}
