package ganggang3.gang.Service_en;
import ganggang3.gang.Repository.MyplaceCourseRepository;
import ganggang3.gang.domain.Course;
import ganggang3.gang.domain.MyplaceCourse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MyplaceCourseService {
    private final MyplaceCourseRepository myplaceCourseRepository;

    public List<MyplaceCourse> findAllByCourse(Course course){
        return myplaceCourseRepository.findAllByCourse(course);
    }

    public List<MyplaceCourse> findAllByCourseId(long courseid) {
        return myplaceCourseRepository.findAllByCourseId(courseid);
    }

    @Transactional
    public void deleteAllByCourse(Course course) {
        myplaceCourseRepository.deleteAllByCourse(course);
    }
}
