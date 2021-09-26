package ganggang3.gang.Api;

import com.fasterxml.jackson.databind.ObjectMapper;
import ganggang3.gang.Service.MemberService;
import ganggang3.gang.Service.MyplaceService;
import ganggang3.gang.Service.PlaceService;
import ganggang3.gang.domain.Member;
import ganggang3.gang.domain.Myplace;
import ganggang3.gang.domain.Place;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MyplaceApiControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    MemberService memberService;

    @Autowired
    PlaceService placeService;

    @Autowired
    MyplaceService myplaceService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void findAllMyplace() throws Exception{
        //Given
        mockMvc.perform(get("/api/myplace/findallmyplace/{memberid}","1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].name").value("순천만습지1"))
                .andExpect(jsonPath("$.data[1].name").value("순천만습지2"))
                .andExpect(jsonPath("$.data[2].name").value("순천만습지3"))
                .andExpect(jsonPath("$.data[3].name").value("순천만습지4"))
                .andExpect(jsonPath("$.data[4].name").value("순천만습지5"))
                .andExpect(jsonPath("$.data[5].name").value("순천만습지6"))
                .andExpect(jsonPath("$.data[6].name").value("순천만습지7"))
                .andExpect(jsonPath("$.data[7].name").value("순천만습지8"))
                .andExpect(jsonPath("$.data[8].name").value("순천만습지9"))
                .andExpect(jsonPath("$.data[9].name").value("순천만습지10"))
                .andExpect(jsonPath("$.data[10].name").value("순천만습지99"))
                .andDo(print());
    }
    @Test
    @Transactional
    @Rollback(false)
    public void add() throws Exception{

        //When
        mockMvc.perform(post("/api/myplace/addmyplace/1/2")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        //Then
        mockMvc.perform(get("/api/myplace/findallmyplace/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].name").value("순천만습지1"))
                .andDo(print());
    }
    @Test
    @Transactional
    //@Rollback(false)
    public void deleteByplace() throws Exception{
        //When
        mockMvc.perform(put("/api/myplace/deletemyplacebyplace/1/2")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        //Then
        mockMvc.perform(get("/api/myplace/findallmyplace/{memberid}","1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.length()").value(1))
                .andDo(print());
    }
    @Test
    @Transactional
    @Rollback(false)
    public void deleteBymyplace() throws Exception{
        //When
        mockMvc.perform(put("/api/myplace/deletemyplacebyplace/1/2")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        //Then
        mockMvc.perform(get("/api/myplace/findallmyplace/{memberid}","1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.length()").value(1))
                .andDo(print());
    }
}