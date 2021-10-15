package ganggang3.gang.Service_en;
import ganggang3.gang.Repository.VlogRepository;
import ganggang3.gang.Repository_en.VlogRepository_en;
import ganggang3.gang.domain.Place;
import ganggang3.gang.domain.PlaceVlog;
import ganggang3.gang.domain.Vlog;
import ganggang3.gang.domain_en.PlaceEn;
import ganggang3.gang.domain_en.PlaceVlogEn;
import ganggang3.gang.domain_en.VlogEn;
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
public class VlogService_en {

    private final VlogRepository_en vlogRepository;

    public List<PlaceEn> findPlaceList(String vlogurl) {
        Optional<VlogEn> vlog=vlogRepository.findByUrl(vlogurl);
        List<PlaceVlogEn> placeVlogList=vlog.orElseThrow(()->new NoSuchElementException("vlog가 존재하지 않습니다")).getPlace_vlogList();
        List<PlaceEn> placeList=new ArrayList<>();
        // placeVlogList 에 아무것도 없으면 null이 되므로 null 체크해야한다
        if (placeVlogList != null) {
            for (int i = 0; i < placeVlogList.size(); i++) {
                PlaceVlogEn placeVlog = placeVlogList.get(i);
                placeList.add(placeVlog.getPlace());
            }
        }
        
        return placeList;
    }
}
