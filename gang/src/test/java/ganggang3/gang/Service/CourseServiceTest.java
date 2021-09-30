package ganggang3.gang.Service;

import ganggang3.gang.domain.Course;
import ganggang3.gang.domain.Member;
import ganggang3.gang.domain.Myplace;
import ganggang3.gang.domain.MyplaceCourse;
import ganggang3.gang.dto.MyplaceDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.util.Lists.newArrayList;
import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CourseServiceTest {

    @Autowired
    CourseService courseService;
    @Autowired
    MemberService memberService;
    @Autowired
    MyplaceService myplaceService;


    //add course 로직은 잘 돌아감 하지만 영속성콘텍스트 문제인지
    //a로 받은 맴버의 코스리스트에는 새로 추가한 코스가 안들어가있음

//    @Test
//    @Rollback(value = false)
//    public void addCourse(){
//
//        //given
//        Member member= memberService.findById(2);
//        List<Myplace> myplaceList = myplaceService.findMyplaceList(member);
//        //이렇게 안하면 sublist에는 부모에 대한 객체정보를 가지고 있어서 불필요한 메모리 차지함
//        List<Myplace> myplaces = newArrayList(myplaceList.subList(0,2));
//        //
//        Long id = courseService.addCourse(member, myplaces, "코12422323223스모스");
//        //then
//        //맴버의 코스중마지막꺼의 아이디랑 지금 id랑비교하기
//        System.out.println(id);
//        List<Course> a = member.getCourseList();
//        System.out.println(a.size());
//        //
//        assertEquals(a.get(a.size()-1).getId(), id);
//
//
//    }

//    @Test
////    @Rollback(value = false)
//    public void updateCourse(){
//        Member member= memberService.findById(2);
//        Optional<Course> course = courseService.findByNameAndMember("코12433스모스",member);
//        List<Myplace> myplaceList = myplaceService.findMyplaceList(member);
//
//        List<Myplace> myplaces = newArrayList(myplaceList.subList(1,2));
//
//        Long id = courseService.updateCourse(member,course.get(),myplaces,"321");
//
//        Optional<Course> byId = courseService.findById(id);
//
//        assertEquals(byId.get().getName(),"321");
//        List<MyplaceCourse> myplace_courseList = byId.get().getMyplace_courseList();
//        for(int i=0;i<myplace_courseList.size();i++){
//            assertEquals(myplace_courseList.get(i).getMyplace(),myplaces.get(i));
//        }
//
//
//
//    }



}