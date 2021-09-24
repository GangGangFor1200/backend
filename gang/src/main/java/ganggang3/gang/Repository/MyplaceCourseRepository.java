package ganggang3.gang.Repository;

import ganggang3.gang.domain.Course;
import ganggang3.gang.domain.MyplaceCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyplaceCourseRepository extends JpaRepository<MyplaceCourse, Long> {
    void deleteAllByCourse(Course course);
}
