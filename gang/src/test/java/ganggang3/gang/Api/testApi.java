package ganggang3.gang.Api;


import ganggang3.gang.GangApplication;
import ganggang3.gang.Repository.ProvinceRepository;
import ganggang3.gang.Service.ProvinceService;
import ganggang3.gang.domain.Province;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
//@AutoConfigureMockMvc

@WebMvcTest(ProvinceApiController.class)
public class testApi {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    ProvinceRepository provinceRepository;



    @MockBean
    private ProvinceService provinceService;
    @Test
    public void commonGetTest() throws Exception{
        List<Province> all = provinceRepository.findAll();
        System.out.println(all.size());

//
//        //테스트할 get api 경로 (실제 어플리케이션에서 만든 경로)
//         mockMvc.perform(get("/api/province/findprovince")).
//                 // 기대하는 응답코드
//                 andExpect(status().isOk()).
//                 // 기대하는 결과값
//                 andExpect(content().string("{\n" +
//                         "    \"data\": [\n" +
//                         "        {\n" +
//                         "            \"name\": \"경기도\",\n" +
//                         "            \"cityList\": [\n" +
//                         "                {\n" +
//                         "                    \"name\": \"가평군\"\n" +
//                         "                },\n" +
//                         "                {\n" +
//                         "                    \"name\": \"광명시\"\n" +
//                         "                },\n" +
//                         "                {\n" +
//                         "                    \"name\": \"연천군\"\n" +
//                         "                },\n" +
//                         "                {\n" +
//                         "                    \"name\": \"남양주시\"\n" +
//                         "                },\n" +
//                         "                {\n" +
//                         "                    \"name\": \"파주시\"\n" +
//                         "                }\n" +
//                         "            ]\n" +
//                         "        },\n" +
//                         "        {\n" +
//                         "            \"name\": \"충청도\",\n" +
//                         "            \"cityList\": [\n" +
//                         "                {\n" +
//                         "                    \"name\": \"논산시\"\n" +
//                         "                },\n" +
//                         "                {\n" +
//                         "                    \"name\": \"계룡시\"\n" +
//                         "                },\n" +
//                         "                {\n" +
//                         "                    \"name\": \"공주시\"\n" +
//                         "                },\n" +
//                         "                {\n" +
//                         "                    \"name\": \"홍성군\"\n" +
//                         "                },\n" +
//                         "                {\n" +
//                         "                    \"name\": \"보령시\"\n" +
//                         "                }\n" +
//                         "            ]\n" +
//                         "        },\n" +
//                         "        {\n" +
//                         "            \"name\": \"강원도\",\n" +
//                         "            \"cityList\": []\n" +
//                         "        },\n" +
//                         "        {\n" +
//                         "            \"name\": \"전라도\",\n" +
//                         "            \"cityList\": []\n" +
//                         "        },\n" +
//                         "        {\n" +
//                         "            \"name\": \"경상도\",\n" +
//                         "            \"cityList\": []\n" +
//                         "        }\n" +
//                         "    ]\n" +
//                         "}"))
//                 // 결과 출력
//                 .andDo(print());


    }
}

