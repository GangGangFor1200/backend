package ganggang3.gang.Api;

import com.fasterxml.jackson.databind.ObjectMapper;
import ganggang3.gang.Service.CourseService;
import ganggang3.gang.Service.MemberService;
import ganggang3.gang.Service.MyplaceCourseService;
import ganggang3.gang.Service.MyplaceService;
import ganggang3.gang.domain.Course;
import ganggang3.gang.domain.Member;
import ganggang3.gang.domain.Myplace;
import ganggang3.gang.dto.MyplaceDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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
        Long member_id=1234L;
        Member member=memberService.findById(member_id);
        //when
        mockMvc.perform(get("/api/course/findall/{memberid}",member_id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.length()").value(2))
                .andExpect(jsonPath("$.data[0].name").value("course2"))
                .andDo(print());
        //then
    }
    @Test
    public void findMyplaceAllByCourse() throws Exception {
        //given
        Long course_id=1234L;
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
        Long member_id=1234L;
        Member member=memberService.findById(member_id);
        //test??? member??? ?????? ?????? myplace??? ???????????????, ??????????????? ????????? course??? ?????? myplaceList ???????????????
        List<Myplace> myplaceList = myplaceService.findMyplaceList(member);
        //?????? ?????????????????? myplaceDto??? ???????????????
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
        mockMvc.perform(post("/api/course/add/{member}",member_id)
                .content(content)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

//        findallmycourse??????
        mockMvc.perform(get("/api/course/findall/{member}",member_id))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data[-1].name").value(name))
            .andDo(print());
    }


    @Test
    @Transactional
    @Rollback(false)
    //course save??????
    public void updateCourse() throws Exception {
        //???????????? ??? ?????????????????? ??????????????? ????????? ???
        Long member_id=1234L;
        Member member=memberService.findById(member_id);
        Optional<Course> course = courseService.findByNameAndMember("course1", member);
        Long id = course.get().getId();
        System.out.println(id);
        //?????? ?????? ????????? ???????????? myplaceDTO??? ????????? ????????? DTD??? ??????????????????
        List<Myplace> myplaceList = myplaceService.findMyplaceList(member);
        List<MyplaceDto> myplaceDtoList = new ArrayList<>();
        myplaceList.forEach(myplace -> {
            myplaceDtoList.add(MyplaceDto.of(myplace));
        });
        //when
        //??????????????? ?????? ????????? myplaceList??? ?????? , ?????? test?????? member??? myplace??? ????????? ?????????
        List<MyplaceDto> newDtoList=new ArrayList(myplaceDtoList.subList(0,2));
        int size= newDtoList.size();
        Map<String,Object> map=new HashMap<>();
        String changeedName = "courseChanged1";
        map.put("name",changeedName);
        map.put("myplaceList",newDtoList);
        //then
        String content = objectMapper.writeValueAsString(map);
        System.out.println(content);
        mockMvc.perform(put("/api/course/update/{member}/{course}",member_id,id)
                .content(content)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        //findallmycourse??????
        //?????? ???????????? ??????
        mockMvc.perform(get("/api/course/findall/{member}",member_id))
                .andExpect(status().isOk())
                //?????? ?????? ??????
                .andExpect(jsonPath("$.data[-1].name").value(changeedName))
                .andDo(print());
        //myplaceList update????????? ??????
        mockMvc.perform(get("/api/myplacecourse/findall/{course}",id))
                .andExpect(status().isOk())
                //?????? ?????? ??????
                .andExpect(jsonPath("$.data.length()").value(size))
                .andExpect(jsonPath("$.data[0].myplaceDto.name").value(newDtoList.get(0).getName()))
                .andDo(print());
    }
    //?????? ??????dto???????????? ?????? ??????
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
