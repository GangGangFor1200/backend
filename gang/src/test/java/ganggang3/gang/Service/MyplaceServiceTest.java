package ganggang3.gang.Service;

import ganggang3.gang.Repository.MemberRepository;
import ganggang3.gang.Repository.PlaceRepository;
import ganggang3.gang.Service.MemberService;
import ganggang3.gang.Service.MyplaceService;
import ganggang3.gang.Service.PlaceService;
import ganggang3.gang.domain.Member;
import ganggang3.gang.domain.Myplace;
import ganggang3.gang.domain.Place;
import ganggang3.gang.dto.MyplaceDto;
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
        long memberId=1;

        Member member=memberService.findById(memberId);
        System.out.println(member.getName());

        //when
        List<Myplace> myplaceList=myplaceService.findMyplaceList(member);
        myplaceList.forEach(p-> System.out.println(p.getName()));

        //then
        assertEquals(0,myplaceList.size(),0);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void addMyplace(){
        //given
        Member member = memberService.findById(1);
        Place place= placeService.findById(2);
        //when
        long id=myplaceService.add(member,place);
        //then
        assertNotNull(myplaceService.findByMemberAndName(member,place.getName()));
    }

    @Test
    @Transactional
//    @Rollback(false)
    public void deleteMyplace(){
        //given
        addMyplace();
        Member member = memberService.findById(1);
        Place place= placeService.findById(8);
        //원래 있는 데이터인지 확인
        //이게 없다면 원래 없는데이터여서 그냥 통과할 수 있음
        assertNotNull(myplaceService.findByMemberAndName(member,place.getName()));

        //when
        myplaceService.deleteByPlace(member,place);

        //then
        assertNull(myplaceService.findByMemberAndName(member, place.getName()));

    }

}
