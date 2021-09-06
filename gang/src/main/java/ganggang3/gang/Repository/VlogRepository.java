package ganggang3.gang.Repository;

import ganggang3.gang.domain.Place;
import ganggang3.gang.domain.Place_Vlog;
import ganggang3.gang.domain.Vlog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class VlogRepository {

    private final EntityManager em;

    public Vlog findOne(Long id){
        return em.find(Vlog.class,id);
    }

    public List<Place_Vlog> findPlaceVlogList(long vlogId) {
        Vlog vlog=findOne(vlogId);
        return vlog.getPlace_vlogList();
    }
}
