package ganggang3.gang.Api;

import ganggang3.gang.Service.MyplaceService;
import ganggang3.gang.domain.Member;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MyplaceApiController {
    @Autowired
    MyplaceService myplaceService;



    @Test
    public void findAllMyplace(){
        //given

//        myplaceService.add();
        //when

        //then
    }

    @Test
    public void addMyplace(){
        //given

        //when

        //then
    }
    @Test
    public void deleteMyplace(){
        //given

        //when

        //then
    }

}
