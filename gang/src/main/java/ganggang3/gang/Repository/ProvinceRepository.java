package ganggang3.gang.Repository;

import ganggang3.gang.domain.Province;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public interface ProvinceRepository extends JpaRepository<Province,Long> {


}
