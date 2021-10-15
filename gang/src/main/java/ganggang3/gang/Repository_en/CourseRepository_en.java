package ganggang3.gang.Repository_en;
import ganggang3.gang.domain.Course;
import ganggang3.gang.domain.Member;
import ganggang3.gang.domain_en.CourseEn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository_en extends JpaRepository<CourseEn,Long> {

    List<CourseEn> findAllByMember(Member member);
    Optional<CourseEn> findByNameAndMember(String name, Member member);
    Optional<CourseEn> findById(Long id);

}
