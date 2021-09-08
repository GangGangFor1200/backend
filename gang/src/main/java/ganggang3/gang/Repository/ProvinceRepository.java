package ganggang3.gang.Repository;

import ganggang3.gang.domain.Province;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProvinceRepository {

    private final EntityManager em;

    public List<Province> findAll() {
        return em.createQuery("select m from Province m",Province.class)
                .getResultList();
    }
}
