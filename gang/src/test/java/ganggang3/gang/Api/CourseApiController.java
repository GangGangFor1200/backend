package ganggang3.gang.Api;

import com.fasterxml.jackson.databind.ObjectMapper;
import ganggang3.gang.Service.MemberService;
import ganggang3.gang.Service.MyplaceService;
import ganggang3.gang.domain.Member;
import ganggang3.gang.domain.Myplace;
import ganggang3.gang.dto.MyplaceDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CourseApiController {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MyplaceService myplaceService;
    @Autowired
    MemberService memberService;


    @Test
    public void findAllMyCourse() throws Exception {
        //given

        //when
        mockMvc.perform(get("/api/course/findmycourse/{member}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].myplace_courseList.get(0).getName()").value("순천만습지1"))
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
    public void addCourse() throws Exception {
        //given
        Member member= memberService.findById(1);
        List<Myplace> myplaceList = myplaceService.findMyplaceList(member);


        //when ,then
        //1번 2,3번 myplace 넣기
        String content = objectMapper.writeValueAsString(myplaceList.subList(0,2));

        mockMvc.perform(post("/api/course/add/1")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        //findallmycourse하기
        mockMvc.perform(get("/api/course/findmycourse/1"))
                .andExpect(status().isOk())
                //첫번째코스의 첫번째 장소
                .andExpect(jsonPath("$.data[0].myplace_courseList.get(0).getName()").value("순천만습지1"))
                .andDo(print());
    }



    @Test
    public void deleteCourse(){
        //given

        //when

        //then
    }
}
