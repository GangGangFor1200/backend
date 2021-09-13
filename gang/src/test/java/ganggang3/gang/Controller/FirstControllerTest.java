package ganggang3.gang.Controller;

import ganggang3.gang.Service.CategoryService;
import ganggang3.gang.Service.ProvinceService;
import ganggang3.gang.domain.Category;
import ganggang3.gang.domain.Province;
import ganggang3.gang.domain.City;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class FirstControllerTest {


    @Autowired
    CategoryService categoryService;

    @Autowired
    ProvinceService provinceService;



    @Test
    public void 확인(){
        //given

        //when

        //then

    }
    @Test
    public void categoryList꺼내기(){
        //given -> db에 categorylist가 들어가 있을 때

        //when
        List<Category> categoryList=categoryService.findAll();

        //then
        assertEquals(5,categoryList.size(),0);


    }
    @Test
    public void provinceList꺼내기(){
        //given -> db에 provincelist가 들어가 있을 때


        //when
        List<Province> provinceList=provinceService.findAll();

        //then
        assertEquals(1,provinceList.size(),0);
        assertEquals("광역시",provinceList.get(0).getName());
    }

    @Test
    public void provincelist에서station꺼내기(){
        //given -> db에 province와 city가 들어가 있을 때

        //when
        List<Province> provinceList=provinceService.findAll();

        //then -> 일단 하나의 province만 test
        List<City> cityList =provinceList.get(0).getCityList();
        assertEquals(6, cityList.size(),0);
        for(int i = 0; i< cityList.size(); i++)
            assertEquals(provinceList.get(0), cityList.get(i).getProvince());
    }
    @Test
    public void category_province_json으로보내기(){
        //given
        Map<String,Object> map=new HashMap<>();
        map.put("province",provinceService.findAll());
        map.put("category",categoryService.findAll());

        //when


        //then

    }
}