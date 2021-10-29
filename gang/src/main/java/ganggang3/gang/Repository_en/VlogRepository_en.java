package ganggang3.gang.Repository_en;

import ganggang3.gang.domain.Vlog;
import ganggang3.gang.domain_en.VlogEn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VlogRepository_en extends JpaRepository<VlogEn,Long> {

    Optional<VlogEn> findById(Long id);

    List<VlogEn> findByUrl(String vlogurl);
}
