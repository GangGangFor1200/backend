package ganggang3.gang.Api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CourseApiController {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void findAllMyCourse() throws Exception {
        //given

        //when
        mockMvc.perform(get("/api/province/findprovince"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].name").value("경기도"))
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
    public void addCourseToMember(){
        //given

        //when

        //then
    }
    @Test
    public void deleteCourseFromMember(){
        //given

        //when

        //then
    }
}
