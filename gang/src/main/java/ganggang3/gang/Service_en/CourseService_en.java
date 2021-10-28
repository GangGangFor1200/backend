package ganggang3.gang.Service_en;
import ganggang3.gang.Repository.CourseRepository;
import ganggang3.gang.Repository.MemberRepository;
import ganggang3.gang.Repository.MyplaceCourseRepository;
import ganggang3.gang.Repository_en.CourseRepository_en;
import ganggang3.gang.Repository_en.MyplaceCourseRepository_en;
import ganggang3.gang.domain.Course;
import ganggang3.gang.domain.Member;
import ganggang3.gang.domain.Myplace;
import ganggang3.gang.domain.MyplaceCourse;
import ganggang3.gang.domain_en.CourseEn;
import ganggang3.gang.domain_en.MyplaceCourseEn;
import ganggang3.gang.domain_en.MyplaceEn;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CourseService_en {
    private final CourseRepository_en courseRepository;
    private final MemberRepository memberRepository;
    private final MyplaceCourseRepository_en myplaceCourseRepository;

    @Transactional
    public Long addCourse(Member member, List<MyplaceEn> myplaceList, String name){
        List<CourseEn> courseList = member.getCourseListEn();

        CourseEn course= CourseEn.createCourse(name, member);
        addmyplaceCourse(course, myplaceList);

        CourseEn save = courseRepository.save(course);

        return save.getId();

    }

    @Transactional
    //지금은 업데이트할때 현재 코스안에 있는거 모두 지우고 다시 넣는데
    //추후 바뀐거만 개선하기 - 순서까지 다 고려해야함
    public Long updateCourse(Member member, CourseEn course, List<MyplaceEn> myplaceList, String name){

        Optional<CourseEn> byId = courseRepository.findById(course.getId());
        byId.orElseThrow(()->new NoSuchElementException("코스가 존재하지 않습니다"));
        myplaceCourseRepository.deleteAllByCourse(byId.get());
        addmyplaceCourse(course, myplaceList);
        if (!byId.get().getName().equals(name))
            byId.get().setName(name);

        CourseEn save = courseRepository.save(course);
        return save.getId();

    }


    private void addmyplaceCourse(CourseEn course, List<MyplaceEn> myplaceList) {
        if (myplaceList!=null) {
            myplaceList.forEach(mp -> {
                        MyplaceCourseEn myplaceCourse = MyplaceCourseEn.createMyplaceCourse(mp, course);
                        myplaceCourseRepository.save(myplaceCourse);
                    }
            );
        }
    }
    public List<CourseEn> findAllByMember(Member member){
        return courseRepository.findAllByMember(member);
    }
    public Optional<CourseEn> findByNameAndMember(String name, Member member){
        return courseRepository.findByNameAndMember(name,member);
    }
    public Optional<CourseEn> findById(Long id){
        return courseRepository.findById(id);
    }

    @Transactional
    public void deleteCourse(Long courseid) {
        Optional<CourseEn> ById = courseRepository.findById(courseid);
        CourseEn course=ById.orElseThrow(()->new NoSuchElementException("코스가 존재하지 않습니다"));
        //CourseEn지우면 해당 CourseEn의 MyplaceCourseEnList까지 다 지워짐
        courseRepository.delete(course);
    }
}
