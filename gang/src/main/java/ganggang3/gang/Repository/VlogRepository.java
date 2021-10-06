package ganggang3.gang.Repository;

import ganggang3.gang.domain.PlaceVlog;
import ganggang3.gang.domain.Vlog;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public interface VlogRepository extends JpaRepository<Vlog,Long> {

    Optional<Vlog> findById(Long id);

    Optional<Vlog> findByUrl(String vlogurl);
}
