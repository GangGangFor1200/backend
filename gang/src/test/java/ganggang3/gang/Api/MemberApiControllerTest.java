package ganggang3.gang.Api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@SpringBootTest
@AutoConfigureMockMvc
public class MemberApiControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @WithMockUser
    public void 로그인() throws Exception{
        //Given
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    @WithMockUser
    public void mypage() throws Exception{
        //Given
        mockMvc.perform(get("/mypage"))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }
    @Test
    @Transactional
    @Rollback(false)
    public void 회원가입()throws Exception{
        //Given
        Map<String,String> map=new HashMap<>();
        map.put("username","주으리");
        map.put("password","1234");
        String content=objectMapper.writeValueAsString(map);

        //Then
        mockMvc.perform(post("/api/savemember")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

}