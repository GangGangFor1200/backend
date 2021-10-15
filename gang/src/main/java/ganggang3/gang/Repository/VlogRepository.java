package ganggang3.gang.Repository;

import ganggang3.gang.domain.VlogEn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VlogRepository extends JpaRepository<VlogEn,Long> {

    Optional<VlogEn> findById(Long id);

    Optional<VlogEn> findByUrl(String vlogurl);
}
