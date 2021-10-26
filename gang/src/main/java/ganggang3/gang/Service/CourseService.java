package ganggang3.gang.Service;

import ganggang3.gang.Repository.CourseRepository;
import ganggang3.gang.Repository.MemberRepository;
import ganggang3.gang.Repository.MyplaceCourseRepository;
import ganggang3.gang.domain.Course;
import ganggang3.gang.domain.Member;
import ganggang3.gang.domain.Myplace;
import ganggang3.gang.domain.MyplaceCourse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
        Course course= Course.createCourse(name, member);
        addmyplaceCourse(course, myplaceList);

        Course save = courseRepository.save(course);

        return save.getId();

    }

    @Transactional
    //지금은 업데이트할때 현재 코스안에 있는거 모두 지우고 다시 넣는데
    //추후 바뀐거만 개선하기 - 순서까지 다 고려해야함
    public Long updateCourse(Member member, Course course, List<Myplace> myplaceList, String name){

        Optional<Course> byId = courseRepository.findById(course.getId());
        byId.orElseThrow(()->new NoSuchElementException("코스가 존재하지 않습니다"));
        myplaceCourseRepository.deleteAllByCourse(byId.get());
        addmyplaceCourse(course, myplaceList);
        if (!byId.get().getName().equals(name))
            byId.get().setName(name);

        Course save = courseRepository.save(course);
        return save.getId();

    }


    private void addmyplaceCourse(Course course, List<Myplace> myplaceList) {
        if (myplaceList!=null) {
            myplaceList.forEach(mp -> {
                        MyplaceCourse myplaceCourse = MyplaceCourse.createMyplaceCourse(mp, course);
                        myplaceCourseRepository.save(myplaceCourse);
                    }
            );
        }
    }
    public List<Course> findAllByMember(Member member){
        return courseRepository.findAllByMember(member);
    }
    public Optional<Course> findByNameAndMember(String name, Member member){
        return courseRepository.findByNameAndMember(name,member);
    }
    public Optional<Course> findById(Long id){
        return courseRepository.findById(id);
    }

    @Transactional
    public void deleteCourse(Long courseid) {
        Optional<Course> ById = courseRepository.findById(courseid);
        Course course=ById.orElseThrow(()->new NoSuchElementException("코스가 존재하지 않습니다"));
        //course지우면 해당 course의 MyplaceCourseList까지 다 지워짐
        courseRepository.delete(course);
    }
}
