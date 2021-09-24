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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final MemberRepository memberRepository;
    private final MyplaceCourseRepository myplaceCourseRepository;


    public Long addCourse(Member member, List<Myplace> myplaceList, String name){
        List<Course> courseList = member.getCourseList();

        Course byNameAndMember = courseRepository.findByNameAndMember(name, member);
        //이미 존재하면 update
        if (byNameAndMember!=null){
            //오류 발생 이미 있는 코스이름입니다.
            throw new IllegalStateException("이미 있는 코스 이름입니다.");
        }

        Course course= Course.createCourse(name, member);
        myplaceList.forEach(mp->{
            MyplaceCourse myplaceCourse = MyplaceCourse.createMyplaceCourse(mp, course);
            System.out.println(mp.getName());
            //이거 cascade땜에 안해도됨
//            myplaceCourseRepository.save(myplaceCourse);
            }
        );

        courseList.add(course);
        Course save = courseRepository.save(course);


        return save.getId();

    }
}
