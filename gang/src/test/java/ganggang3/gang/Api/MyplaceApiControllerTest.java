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
                .andExpect(jsonPath("$.data[1].name").value("순천만습지4"))
                .andDo(print());
    }
    @Test
    @Transactional
    @Rollback(false)
    public void add() throws Exception{

        //When
        mockMvc.perform(post("/api/myplace/addmyplace/4/6")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        //Then
        mockMvc.perform(get("/api/myplace/findallmyplace/4"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[-1].name").value("순천만습지6"))
                .andDo(print());
    }
    @Test
    @Transactional
    @Rollback(false)
    public void  deleteByplace() throws Exception{
        //When
        mockMvc.perform(put("/api/myplace/deletemyplacebyplace/4/6")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        //Then
        mockMvc.perform(get("/api/myplace/findallmyplace/{memberid}","4"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.length()").value(1))
                .andDo(print());
    }

}