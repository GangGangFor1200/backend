package ganggang3.gang.Repository;

import ganggang3.gang.domain.ProvinceEn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceRepository extends JpaRepository<ProvinceEn,Long> {


}
