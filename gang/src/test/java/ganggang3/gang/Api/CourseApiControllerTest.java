package ganggang3.gang.Api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.istack.NotNull;
import ganggang3.gang.Service.CourseService;
import ganggang3.gang.Service.MemberService;
import ganggang3.gang.Service.MyplaceCourseService;
import ganggang3.gang.Service.MyplaceService;
import ganggang3.gang.domain.Course;
import ganggang3.gang.domain.Member;
import ganggang3.gang.domain.Myplace;
import ganggang3.gang.dto.MyplaceDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.transaction.Transactional;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CourseApiControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MyplaceService myplaceService;
    @Autowired
    MemberService memberService;
    @Autowired
    CourseService courseService;
    @Autowired
    MyplaceCourseService myplaceCourseService;


    @Test
    public void findAllByMember() throws Exception {
        //given

        //when
        mockMvc.perform(get("/api/course/findAll/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.length()").value(2))
                .andExpect(jsonPath("$.data[0].name").value("course2"))
                .andDo(print());
        //then
    }
    @Test
    public void findMyplaceAllByCourse() throws Exception {
        //given
        long course_id=24;// -> course id로 찾는게 더 사용자입장? 이긴 함
        //when
        mockMvc.perform(get("/api/myplacecourse/findall/{course}",course_id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.length()").value(11))
                .andDo(print());

        //then

    }

    @Test
    @Transactional
    @Rollback(false)
    public void addCourse() throws Exception {
        //given
        Member member= memberService.findById(0L);
        //test는 member가 가진 모든 myplace를 가져오지만, 프론트에서 추가할 course에 넣을 myplaceList 넘겨줄거임
        List<Myplace> myplaceList = myplaceService.findMyplaceList(member);
        //실제 프론트에서는 myplaceDto가 넘어올거임
        List<MyplaceDto> myplaceDtoList = new ArrayList<>();
        myplaceList.forEach(myplace -> {
            myplaceDtoList.add(MyplaceDto.of(myplace));
        });
        Map<String,Object> map=new HashMap<>();
        String name = "course1";
        map.put("name",name);
        map.put("myplaceList",myplaceDtoList);
        System.out.println(map);

        //when ,then
        String content = objectMapper.writeValueAsString(map);
        System.out.println(content);
        mockMvc.perform(post("/api/course/add/{memer}",member.getId())
                .content(content)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

//        findallmycourse하기
        mockMvc.perform(get("/api/course/findAll/{member}",member.getId()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data[-1].name").value(name))
            .andDo(print());
    }


    @Test
    @Transactional
    @Rollback(false)
    //course save버튼
    public void updateCourse() throws Exception {
        //테스트할 때 맴버이름하고 코스이름만 바꾸면 됨
        Member member= memberService.findById(0L);
        //memberid 로 member, course id로 course가 누구인지 알아오지않나?-> 이름이 더 직관적이긴하지
        Optional<Course> course = courseService.findByNameAndMember("course1", member);
        Long id = course.get().getId();
        System.out.println(id);
        //이게 실제 페이지 상에서는 myplaceDTO로 넘어옴 그래서 DTD로 테스트해야함
        List<Myplace> myplaceList = myplaceService.findMyplaceList(member);
        List<MyplaceDto> myplaceDtoList = new ArrayList<>();
        myplaceList.forEach(myplace -> {
            myplaceDtoList.add(MyplaceDto.of(myplace));
        });
        //when
        //프론트에서 주는 수정된 myplaceList로 변경 , 일단 test에선 member의 myplace중 일부를 가져옴
        List<MyplaceDto> newDtoList=new ArrayList(myplaceDtoList.subList(0,2));
        int size= newDtoList.size();
        Map<String,Object> map=new HashMap<>();
        String changeedName = "courseChanged1";
        map.put("name",changeedName);
        map.put("myplaceList",newDtoList);
        //then
        String content = objectMapper.writeValueAsString(map);
        System.out.println(content);
        mockMvc.perform(put("/api/course/update/{member}/{course}",member.getId(),id)
                .content(content)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        //findallmycourse하기
        //이름 바뀐건지 확인
        mockMvc.perform(get("/api/course/findAll/{member}",member.getId()))
                .andExpect(status().isOk())
                //바뀐 이름 체크
                .andExpect(jsonPath("$.data[-1].name").value(changeedName))
                .andDo(print());
        //myplaceList update됐는지 확인
        mockMvc.perform(get("/api/myplacecourse/findall/{course}",id))
                .andExpect(status().isOk())
                //바뀐 이름 체크
                .andExpect(jsonPath("$.data.length()").value(size))
                .andExpect(jsonPath("$.data[0].myplaceDto.name").value(newDtoList.get(0).getName()))
                .andDo(print());
    }
    //이거 코스dto관련해서 다시 생각
    @Test
    @Transactional
    @Rollback(false)
    public void deleteCourse() throws Exception{
        //given
        long courseid=46;
        //when
        mockMvc.perform(delete("/api/course/delete/{courseid}",courseid))
                .andExpect(status().isOk())
                .andDo(print());
        //then
        assertEquals(courseService.findById(courseid),Optional.empty());
        assertEquals(myplaceCourseService.findAllByCourseId(courseid).size(),0,0);
    }
}
