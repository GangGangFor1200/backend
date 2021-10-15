package ganggang3.gang.Repository;



import ganggang3.gang.domain.CategoryEn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEn,Long> {

    Optional<CategoryEn> findById(long id);

}
