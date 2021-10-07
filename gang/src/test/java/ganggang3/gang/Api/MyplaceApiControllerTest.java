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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

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
 //   @WithMockUser(username = "주리링1")
    public void findAllMyplace() throws Exception{
            //Given
            mockMvc.perform(get("/api/myplace/findAll/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].name").value("순천만습지1"))
                .andDo(print());
    }

    @Test
    @Transactional
    @Rollback(false)
    //@WithMockUser(username = "주리링1")
    public void add() throws Exception{

        //When
        mockMvc.perform(post("/api/myplace/add/2/4")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    @Transactional
    @Rollback(false)
    public void addFromApi() throws Exception{
        //given
        Member member =memberService.findById(1L);
        Map<String,Object> map=new HashMap<>();
        String name="어린이대공원";
        map.put("name",name);
        map.put("category","관광명소");
        map.put("location_x",123.23);
        map.put("location_y",37.32);
        map.put("address","서울시 광진구 군자로 98");
        String content=objectMapper.writeValueAsString(map);
        //원래는 api로 받아와야한다
        //when
        mockMvc.perform(post("/api/myplace/addfromapi/1")
                    .content(content)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        //then
        mockMvc.perform(get("/api/myplace/findall/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[-1].name").value(name))
                .andDo(print());

    }
    @Test
    @Transactional
//    @Rollback(false)
    public void  deleteMyplaceByPlace() throws Exception{
        //When
        mockMvc.perform(delete("/api/myplace/deletebyplace/2/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        //Then

        mockMvc.perform(get("/api/myplace/findAll/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.length()").value(1))
                .andDo(print());
    }

    @Test
    @Transactional
    @Rollback(false)
    @WithMockUser(username = "주리링1")
    public void  deleteMyplaceByMyplace() throws Exception{
        //When
        mockMvc.perform(put("/api/myplace/deletebymyplace/33")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        //Then
        mockMvc.perform(get("/api/myplace/findallmyplace"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.length()").value(1))
                .andDo(print());
    }

}