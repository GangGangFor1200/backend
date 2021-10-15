package ganggang3.gang.Service_en;
import ganggang3.gang.Repository.VlogRepository;
import ganggang3.gang.domain.PlaceEn;
import ganggang3.gang.domain.PlaceVlog;
import ganggang3.gang.domain.VlogEn;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class VlogService {

    private final VlogRepository vlogRepository;

    public List<PlaceEn> findPlaceList(String vlogurl) {
        Optional<VlogEn> vlog=vlogRepository.findByUrl(vlogurl);
        List<PlaceVlog> placeVlogList=vlog.orElseThrow(()->new NoSuchElementException("vlog가 존재하지 않습니다")).getPlace_vlogList();
        List<PlaceEn> placeList=new ArrayList<>();
        // placeVlogList 에 아무것도 없으면 null이 되므로 null 체크해야한다
        if (placeVlogList != null) {
            for (int i = 0; i < placeVlogList.size(); i++) {
                PlaceVlog placeVlog = placeVlogList.get(i);
                placeList.add(placeVlog.getPlace());
            }
        }
        
        return placeList;
    }
}
