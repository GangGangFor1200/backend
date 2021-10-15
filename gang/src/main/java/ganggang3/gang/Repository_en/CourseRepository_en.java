package ganggang3.gang.Repository_en;
import ganggang3.gang.domain.CourseEn;
import ganggang3.gang.domain.MemberEn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository_en extends JpaRepository<CourseEn,Long> {

    List<CourseEn> findAllByMember(MemberEn member);
    Optional<CourseEn> findByNameAndMember(String name, MemberEn member);
    Optional<CourseEn> findById(Long id);

}
