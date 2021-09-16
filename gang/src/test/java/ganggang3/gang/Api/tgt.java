package ganggang3.gang.Api;

import ganggang3.gang.Repository.ProvinceRepository;
import ganggang3.gang.Service.ProvinceService;
import ganggang3.gang.domain.Province;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest
public class    tgt {


    @Autowired
    ProvinceRepository provinceRepository;

    @Autowired
    private ProvinceService provinceService;

    @Test
    public void commonGetTest() throws Exception {
        List<Province> all = provinceRepository.findAll();
        System.out.println(all.size());

    }
}
