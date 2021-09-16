package ganggang3.gang.Api;

import com.fasterxml.jackson.databind.ObjectMapper;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class tt {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void province_test() throws Exception {
        // Given
//        ProvinceApiController provinceApiController = new ProvinceApiController;

//        ProvinceApiController.ProvinceDto provinceDto = new ProvinceApiController.ProvinceDto("name","");
//
//        UpdatePostRequest updatePostRequest = new UpdatePostRequest("title", "body");

        mockMvc.perform(get("/api/province/findprovince")).
                 andExpect(status().isOk()).andExpect(jsonPath("$.data[0].name").value("경기도"))
                .andDo(print());
    }
}
