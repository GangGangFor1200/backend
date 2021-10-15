package ganggang3.gang.Service;

import ganggang3.gang.Repository.CourseRepository;
import ganggang3.gang.Repository.MemberRepository;
import ganggang3.gang.Repository.MyplaceCourseRepository;
import ganggang3.gang.domain.CourseEn;
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
        List<CourseEn> courseList = member.getCourseList();

        //Optional로 null 검사
        Optional<CourseEn> byNameAndMember = courseRepository.findByNameAndMember(name, member);
        if (byNameAndMember.isPresent()) {
            throw new NoSuchElementException("존재하는 이름입니다");
        }

        CourseEn course= CourseEn.createCourse(name, member);
        addmyplaceCourse(course, myplaceList);

        CourseEn save = courseRepository.save(course);

        return save.getId();

    }

    @Transactional
    //지금은 업데이트할때 현재 코스안에 있는거 모두 지우고 다시 넣는데
    //추후 바뀐거만 개선하기 - 순서까지 다 고려해야함
    public Long updateCourse(Member member, CourseEn course, List<Myplace> myplaceList, String name){

        Optional<CourseEn> byId = courseRepository.findById(course.getId());
        byId.orElseThrow(()->new NoSuchElementException("코스가 존재하지 않습니다"));
        myplaceCourseRepository.deleteAllByCourse(byId.get());
        addmyplaceCourse(course, myplaceList);
        if (!byId.get().getName().equals(name))
            byId.get().setName(name);

        CourseEn save = courseRepository.save(course);
        return save.getId();

    }


    private void addmyplaceCourse(CourseEn course, List<Myplace> myplaceList) {
        if (myplaceList!=null) {
            myplaceList.forEach(mp -> {
                        MyplaceCourse myplaceCourse = MyplaceCourse.createMyplaceCourse(mp, course);
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
        //course지우면 해당 course의 MyplaceCourseList까지 다 지워짐
        courseRepository.delete(course);
    }
}
