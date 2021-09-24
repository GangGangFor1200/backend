package ganggang3.gang.Service;

import ganggang3.gang.Repository.CourseRepository;
import ganggang3.gang.Repository.MemberRepository;
import ganggang3.gang.Repository.MyplaceCourseRepository;
import ganggang3.gang.domain.Course;
import ganggang3.gang.domain.Member;
import ganggang3.gang.domain.Myplace;
import ganggang3.gang.domain.MyplaceCourse;
import ganggang3.gang.dto.MyplaceDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.springframework.boot.devtools.env.DevToolsPropertyDefaultsPostProcessor.logger;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final MemberRepository memberRepository;
    private final MyplaceCourseRepository myplaceCourseRepository;

    @Transactional
    public Long addCourse(Member member, List<Myplace> myplaceList, String name){
        List<Course> courseList = member.getCourseList();

        Course byNameAndMember = courseRepository.findByNameAndMember(name, member);
        //이미 존재하면 update
        if (byNameAndMember!=null){
            //오류 발생 이미 있는 코스이름입니다.
            throw new IllegalStateException("이미 있는 코스 이름입니다.");
        }

        Course course= Course.createCourse(name, member);
        addmyplaceCourse(course, myplaceList);

        courseList.add(course);
        Course save = courseRepository.save(course);
        return save.getId();

    }
    @Transactional
    public Long updateCourse(Member member, Course course, List<Myplace> myplaceList, String name){
        Optional<Course> byId = courseRepository.findById(course.getId());
        if (byId==null){
            throw new IllegalStateException("코스가 존재하지 않습니다");
        }
        myplaceCourseRepository.deleteAllByCourse(byId.get());

        addmyplaceCourse(course, myplaceList);

        byId.get().setName(name);

        Course save = courseRepository.save(course);
        return save.getId();
    }

    private void addmyplaceCourse(Course course, List<Myplace> myplaceList) {
        myplaceList.forEach(mp -> {
                    MyplaceCourse myplaceCourse = MyplaceCourse.createMyplaceCourse(mp, course);
                    myplaceCourseRepository.save(myplaceCourse);
                }
        );
    }

    public Course findByNameAndMember(String name, Member member){
        return courseRepository.findByNameAndMember(name,member);
    }
    public Optional<Course> findById(Long id){
        return courseRepository.findById(id);
    }
}
