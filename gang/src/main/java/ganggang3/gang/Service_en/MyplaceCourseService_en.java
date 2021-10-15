package ganggang3.gang.Service_en;
import ganggang3.gang.Repository.MyplaceCourseRepository;
import ganggang3.gang.Repository_en.MyplaceCourseRepository_en;
import ganggang3.gang.domain.Course;
import ganggang3.gang.domain.MyplaceCourse;
import ganggang3.gang.domain_en.CourseEn;
import ganggang3.gang.domain_en.MyplaceCourseEn;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MyplaceCourseService_en {
    private final MyplaceCourseRepository_en myplaceCourseRepository;

    public List<MyplaceCourseEn> findAllByCourse(CourseEn course){
        return myplaceCourseRepository.findAllByCourse(course);
    }

    public List<MyplaceCourseEn> findAllByCourseId(long courseid) {
        return myplaceCourseRepository.findAllByCourseId(courseid);
    }

    @Transactional
    public void deleteAllByCourse(CourseEn course) {
        myplaceCourseRepository.deleteAllByCourse(course);
    }
}
