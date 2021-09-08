package ganggang3.gang.Controller;

import ganggang3.gang.Service.PlaceService;
import ganggang3.gang.Service.VlogService;
import ganggang3.gang.domain.Place;
import ganggang3.gang.domain.Place_Vlog;
import ganggang3.gang.domain.Vlog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SecondControllerTest {

    @Autowired
    PlaceService placeService;
    @Autowired
    VlogService vlogService;

    @Test
    public void 확인(){

    }
    @Test
    public void findplace(){
        //given
        long placeId=1;

        //when
        long placenew=placeService.find(placeId);

        //then
        assertEquals(placeId,placenew,0);

    }
    @Test
    public void stationcategory이용해서placeList가져오기(){
        //given -> station,category id가 주어졌을 때
        long stationId=1;
        long categoryId=1;

        //when
        List<Place> placeList=placeService.findPlaceList(stationId,categoryId);

        //then
        //해당 조건의 모든 place를 찾았는지 확인
        assertEquals(10,placeList.size(),0);
        System.out.println(placeList.size());
        //찾은 place들의 station,category id가 지정한 station,category id인지 확인
        for(int i=0;i<placeList.size();i++){
            Place place=placeList.get(i);
            assertEquals(stationId,place.getStation().getId(),0);
            assertEquals(categoryId,place.getCategory().getId(),0);
        }
    }
    @Test
    public void TOP5가져오기(){
        //given
        long stationId=1;
        long categoryId=1;

        //when
        List<Place> TOP5=placeService.findTOP5(stationId,categoryId);

        //then
        assertEquals(5,TOP5.size(),0);
        for(int i=0;i<5;i++){
            Place place=TOP5.get(i);
            System.out.println(place.getId()+" "+place.getPlace_vlogList().size());
            assertEquals(stationId,place.getStation().getId(),0);
            assertEquals(categoryId,place.getCategory().getId(),0);
        }

    }
    @Test
    public void placeid이용해서placevlogList가져오기(){
        //given
        long placeId=1;

        //when
        List<Place_Vlog> placeVlogList= placeService.findPlaceVlogList(placeId);

        //then
        assertEquals(7,placeVlogList.size(),0);
        for(int i=0;i<placeVlogList.size();i++){
            Place_Vlog place_vlog=placeVlogList.get(i);
            assertEquals(placeId,place_vlog.getPlace().getId(),0);
        }

    }
    @Test
    public void Placeid이용해서VlogList가져오기(){
        //given
        long placeId=1;

        //when
        List<Vlog> vlogList=placeService.findVlogList(placeId);

        //then
        assertEquals(7,vlogList.size(),0);

    }
    @Test
    public void vlogid이용해서placevlogList가져오기(){
        //given
        long vlogId=1;

        //when
        List<Place_Vlog> placeVlogList= vlogService.findPlaceVlogList(vlogId);

        //then
        assertEquals(10,placeVlogList.size(),0);
        for(int i=0;i<placeVlogList.size();i++){
            Place_Vlog place_vlog=placeVlogList.get(i);
            assertEquals(vlogId,place_vlog.getVlog().getId(),0);
        }

    }
    @Test
    public void vlogid이용해서PlaceList가져오기(){
        //given
        long vlogId=1;

        //when
        List<Place> placeList=vlogService.findPlaceList(vlogId);

        //then
        assertEquals(10,placeList.size(),0);

    }

}