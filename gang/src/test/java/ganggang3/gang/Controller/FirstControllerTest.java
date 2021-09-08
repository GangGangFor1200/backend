package ganggang3.gang.Controller;

import ganggang3.gang.Repository.CategoryRepository;
import ganggang3.gang.Repository.ProvinceRepository;
import ganggang3.gang.Service.CategoryService;
import ganggang3.gang.Service.PlaceService;
import ganggang3.gang.domain.Category;
import ganggang3.gang.domain.Province;
import ganggang3.gang.domain.Station;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class FirstControllerTest {


    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProvinceRepository provinceRepository;

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
        List<Category> categoryList=categoryRepository.findAll();

        //then
        assertEquals(5,categoryList.size(),0);


    }
    @Test
    public void provinceList꺼내기(){
        //given -> db에 provincelist가 들어가 있을 때


        //when
        List<Province> provinceList=provinceRepository.findAll();

        //then
        assertEquals(1,provinceList.size(),0);
        assertEquals("광역시",provinceList.get(0).getName());
    }

    @Test
    public void provincelist에서station꺼내기(){
        //given -> db에 province와 station이 들어가 있을 때

        //when
        List<Province> provinceList=provinceRepository.findAll();

        //then -> 일단 하나의 province만 test
        List<Station> stationList=provinceList.get(0).getStationList();
        assertEquals(6,stationList.size(),0);
        for(int i=0;i<stationList.size();i++)
            assertEquals(provinceList.get(0),stationList.get(i).getProvince());
    }
}