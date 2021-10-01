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

        //Optional로 null 검사
        Optional<Course> byNameAndMember = courseRepository.findByNameAndMember(name, member);
        if (byNameAndMember.isPresent()) {
            throw new NoSuchElementException("존재하는 이름입니다");
        }

        Course course= Course.createCourse(name, member);
        addmyplaceCourse(course, myplaceList);

        //courseList.add(course); -> 왜 하는거지?
        Course save = courseRepository.save(course);

        return save.getId();

    }

    @Transactional
    public Long updateCourse(Member member, Course course, List<Myplace> myplaceList, String name){

        Optional<Course> byId = courseRepository.findById(course.getId());
        byId.orElseThrow(()->new NoSuchElementException("코스가 존재하지 않습니다"));
        myplaceCourseRepository.deleteAllByCourse(byId.get());
        List<MyplaceCourse> myplaceCourseList=myplaceCourseRepository.findAllByCourse(course);


        addmyplaceCourse(course, myplaceList);

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
    public void deleteCourse(long courseid) {
        Optional<Course> ById = courseRepository.findById(courseid);
        Course course=ById.orElseThrow(()->new NoSuchElementException("코스가 존재하지 않습니다"));
        //course지우면 해당 course의 MyplaceCourseList까지 다 지워짐
        courseRepository.delete(course);
    }
}
