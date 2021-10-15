package ganggang3.gang.Repository_en;



import ganggang3.gang.domain.CategoryEn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository_en extends JpaRepository<CategoryEn,Long> {

    Optional<CategoryEn> findById(long id);

}