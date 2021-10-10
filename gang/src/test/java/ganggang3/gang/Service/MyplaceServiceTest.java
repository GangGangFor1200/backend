package ganggang3.gang.Service;

import ganggang3.gang.Repository.MemberRepository;
import ganggang3.gang.Repository.PlaceRepository;
import ganggang3.gang.domain.Member;
import ganggang3.gang.domain.Myplace;
import ganggang3.gang.domain.Place;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional(readOnly = true)
public class MyplaceServiceTest {

    @Autowired
    MyplaceService myplaceService;
    @Autowired
    MemberService memberService;
    @Autowired
    PlaceService placeService;
    @Autowired
    PlaceRepository placeRepository;
    @Autowired
    MemberRepository memberRepository;


    @Test
    public void 확인(){

    }



    @Test
    public void findAllMyplace(){
        //given
        String access="jPDl5c7aAbAlVmxCz-CYlJ8qpj6OorCgZZPA4AopcJ8AAAF8Y3LQQQ";

        Member member=memberService.findById(access);

        //when
        List<Myplace> myplaceList=myplaceService.findMyplaceList(member);
        myplaceList.forEach(p-> System.out.println(p.getName()));

        //then
        assertEquals(4,myplaceList.size(),0);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void addMyplace(){
        //given
        String access="jPDl5c7aAbAlVmxCz-CYlJ8qpj6OorCgZZPA4AopcJ8AAAF8Y3LQQQ";
        Member member = memberService.findById(access);
        Place place= placeService.findById(5L);
        //when
        long id=myplaceService.add(member,place);
        //then
        assertNotNull(myplaceService.findByMemberAndName(member,place.getName()));
    }


    @Test
    @Transactional
    //@Rollback(false)
    public void deleteMyplace(){
        //given
        String access="jPDl5c7aAbAlVmxCz-CYlJ8qpj6OorCgZZPA4AopcJ8AAAF8Y3LQQQ";
        Member member = memberService.findById(access);
        Place place= placeService.findById(3L);
        //원래 있는 데이터인지 확인
        //이게 없다면 원래 없는데이터여서 그냥 통과할 수 있음
        //assertNotNull(myplaceService.findByMemberAndName(member,place.getName()));

        //when
        myplaceService.deleteByPlace(member,place);

        //then
        assertNull(myplaceService.findByMemberAndName(member, place.getName()));

    }

}
