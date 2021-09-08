package ganggang3.gang.Repository;

import ganggang3.gang.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryRepository {

    private final EntityManager em;
    public List<Category> findAll() {
        return em.createQuery("select m from Category m",Category.class)
                .getResultList();
    }
}
