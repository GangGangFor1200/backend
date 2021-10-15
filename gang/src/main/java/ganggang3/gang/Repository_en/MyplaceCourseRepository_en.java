package ganggang3.gang.Repository_en;

import ganggang3.gang.domain.CourseEn;
import ganggang3.gang.domain.MyplaceCourse;
import ganggang3.gang.domain_en.MyplaceCourseEn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyplaceCourseRepository_en extends JpaRepository<MyplaceCourseEn, Long> {
    void deleteAllByCourse(CourseEn course);
    List<MyplaceCourse> findAllByCourse(CourseEn course);

    List<MyplaceCourse> findAllByCourseId(Long courseid);
}
