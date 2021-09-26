package ganggang3.gang.Api;

import com.fasterxml.jackson.databind.ObjectMapper;
import ganggang3.gang.Repository.ProvinceRepository;
import ganggang3.gang.Service.CategoryService;
import ganggang3.gang.dto.PlaceDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PageApiTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ProvinceRepository provinceRepository;

    @Autowired
    CategoryService categoryService;

    @Test
    public void firstpageTest() throws Exception{

        mockMvc.perform(get("/api/category/findcategory"))
                .andExpect(jsonPath("$.data[0].name").value("관광명소"))
                .andExpect(jsonPath("$.data.length()").value(3))
                .andExpect(status().isOk())
                .andDo(print());
        mockMvc.perform(get("/api/province/findprovince"))
                .andExpect(jsonPath("$.data[0].name").value("경기도"))
                .andExpect(jsonPath("$.data.length()").value(14))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void secondpageTest() throws Exception{
        mockMvc.perform(get("/api/place/findtop5/{city}/{category}","1","1"))
                .andExpect(jsonPath("$.data[0].name").value("순천만습지1"))
                .andExpect(jsonPath("$.data.length()").value(5))
                .andExpect(jsonPath("$.data[0].vlog_list[0].id").value(1))
                .andExpect(jsonPath("$.data[0].vlog_list.length()").value(7))
                .andExpect(status().isOk())
                .andDo(print());

        mockMvc.perform(get("/api/vlog/findplace/{vlog}",1))
                .andExpect(jsonPath("$.data[0].name").value("순천만습지1"))
                .andExpect(jsonPath("$.data.length()").value(10))
                .andExpect(status().isOk())
                .andDo(print());

    }
}
