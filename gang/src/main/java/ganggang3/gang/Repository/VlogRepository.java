package ganggang3.gang.Repository;

import ganggang3.gang.domain.Vlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VlogRepository extends JpaRepository<Vlog,Long> {

    Optional<Vlog> findById(Long id);

    List<Vlog> findByUrl(String vlogurl);
}
