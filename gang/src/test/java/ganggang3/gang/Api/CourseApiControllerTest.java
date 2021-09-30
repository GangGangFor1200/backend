package ganggang3.gang.Api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.istack.NotNull;
import ganggang3.gang.Service.MemberService;
import ganggang3.gang.Service.MyplaceService;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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


    @Test
    public void findALlByMember() throws Exception {
        //given

        //when
        mockMvc.perform(get("/api/course/findAll/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.length()").value(1))
                .andExpect(jsonPath("$.data[0]..name").value("순천만습지1"))
                .andDo(print());
        //then
    }

    @Test
    //course save버튼
    public void updateCourse(){
        //given

        //when

        //then
    }

    @Test
    @Transactional
    @Rollback(false)
    @WithMockUser(username = "주리링1")
    public void addCourse() throws Exception {
        //given
        Member member= memberService.findByName("주리링1");
        List<Myplace> myplaceList = myplaceService.findMyplaceList(member);
        Map<String,Object> map=new HashMap<>();
        map.put("name","course3");
        map.put("myplaceList",myplaceList);

        //when ,then
        String content = objectMapper.writeValueAsString(map);
        System.out.println(content);
        mockMvc.perform(post("/api/course/add")
                .content(content)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        //findallmycourse하기
//        mockMvc.perform(get("/api/course/findAll/1"))
//            .andExpect(status().isOk())
//            //첫번째코스의 첫번째 장소
//            .andExpect(jsonPath("$.data[0].name").value("순천만습지1"))
//            .andDo(print());
    }



    @Test
    public void deleteCourse(){
        //given

        //when

        //then
    }
}
