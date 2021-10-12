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
    public void findAllMyplace() throws Exception{
        Long member_id=1234L;
            //Given
            mockMvc.perform(get("/api/myplace/findall/{member}",member_id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].name").value("순천만습지1"))
                .andDo(print());
    }

    @Test
    @Transactional
    @Rollback(false)
    public void add() throws Exception{
        Long member_id=1234L;
        //When
        mockMvc.perform(post("/api/myplace/add/{member}/4",member_id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    @Transactional
    @Rollback(false)
    public void addFromApi() throws Exception{
        Long member_id=1234L;
        //given
        Member member =memberService.findById(member_id);
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
        mockMvc.perform(post("/api/myplace/addfromapi/{member}",member_id)
                    .content(content)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        //then
        mockMvc.perform(get("/api/myplace/findall/{member}",member_id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[-1].name").value(name))
                .andDo(print());

    }
    @Test
    @Transactional
//    @Rollback(false)
    public void  deleteMyplaceByPlace() throws Exception{
        Long member_id=1234L;
        //When
        mockMvc.perform(delete("/api/myplace/deletebyplace/{member}/1",member_id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        //Then

        mockMvc.perform(get("/api/myplace/findAll/{member}",member_id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.length()").value(1))
                .andDo(print());
    }

    @Test
    @Transactional
    @Rollback(false)
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