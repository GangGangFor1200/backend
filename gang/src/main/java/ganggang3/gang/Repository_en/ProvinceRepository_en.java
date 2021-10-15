package ganggang3.gang.Repository_en;
import ganggang3.gang.domain.Province;
import ganggang3.gang.domain_en.ProvinceEn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceRepository_en extends JpaRepository<ProvinceEn,Long> {


}
