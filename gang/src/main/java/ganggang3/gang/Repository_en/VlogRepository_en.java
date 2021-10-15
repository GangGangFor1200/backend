package ganggang3.gang.Repository_en;

import ganggang3.gang.domain.Vlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VlogRepository_en extends JpaRepository<Vlog,Long> {

    Optional<Vlog> findById(Long id);

    Optional<Vlog> findByUrl(String vlogurl);
}
