package ganggang3.gang.Repository;

import ganggang3.gang.domain.Course;
import ganggang3.gang.domain.Member;
import ganggang3.gang.domain.Myplace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course,Long> {
//    Course findByNameAndMemberAndMyplace_courseList(String name, Member member, List<Myplace> myplaceList);
    Course findByNameAndMember(String name, Member member);
}
