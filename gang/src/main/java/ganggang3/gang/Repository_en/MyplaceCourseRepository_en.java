package ganggang3.gang.Repository_en;

import ganggang3.gang.domain.Course;
import ganggang3.gang.domain.MyplaceCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyplaceCourseRepository_en extends JpaRepository<ganggang3.gang.domain_en.MyplaceCourseEn, Long> {
    void deleteAllByCourse(Course course);
    List<MyplaceCourse> findAllByCourse(Course course);

    List<MyplaceCourse> findAllByCourseId(Long courseid);
}
