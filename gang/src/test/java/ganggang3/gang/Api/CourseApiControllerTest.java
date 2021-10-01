package ganggang3.gang.Api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.istack.NotNull;
import ganggang3.gang.Service.CourseService;
import ganggang3.gang.Service.MemberService;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.transaction.Transactional;

import java.util.*;

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
    @Transactional
    @Rollback(false)
    public void addCourse() throws Exception {
        //given
        Member member= memberService.findByName("주리링1");
        List<Myplace> myplaceList = myplaceService.findMyplaceList(member);
        Map<String,Object> map=new HashMap<>();
        map.put("name","course7");
        map.put("myplaceList",myplaceList);

        //when ,then
        String content = objectMapper.writeValueAsString(map);
        System.out.println(content);
        mockMvc.perform(post("/api/course/add/1")
                .content(content)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

//        findallmycourse하기
        mockMvc.perform(get("/api/course/findAll/1"))
            .andExpect(status().isOk())

            .andExpect(jsonPath("$.data[-1].name").value("course7"))
            .andDo(print());
    }


    @Test
    @Transactional
//    @Rollback(false)
    //course save버튼
    public void updateCourse() throws Exception {
        Member member= memberService.findByName("주리링1");
        Optional<Course> course = courseService.findByNameAndMember("course7", member);
        Long id = course.get().getId();
        System.out.println(id);
        List<Myplace> myplaceList = myplaceService.findMyplaceList(member);
        myplaceList=new ArrayList(myplaceList.subList(0,1));
        Map<String,Object> map=new HashMap<>();
        map.put("name","courseChanged7");
        map.put("myplaceList",myplaceList);

        //when ,then
        String content = objectMapper.writeValueAsString(map);
        System.out.println(content);
        mockMvc.perform(put("/api/course/update/{member}/{course}",member.getId(),id)
                .content(content)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

//        findallmycourse하기
        mockMvc.perform(get("/api/course/findAll/1"))
                .andExpect(status().isOk())
                //바뀐 이름 체크
                .andExpect(jsonPath("$.data[-1].name").value("courseChanged7"))
                .andDo(print());
        mockMvc.perform(get("/api/myplacecourse/findall/{course}",id))
                .andExpect(status().isOk())
                //바뀐 이름 체크
                .andExpect(jsonPath("$.data.length()").value(1))
                .andDo(print());
    }
    @Test
    public void deleteCourse(){
        //given

        //when

        //then
    }
}
