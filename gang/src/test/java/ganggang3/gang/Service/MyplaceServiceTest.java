package ganggang3.gang.Service;

import ganggang3.gang.Repository.MemberRepository;
import ganggang3.gang.Repository.PlaceRepository;
import ganggang3.gang.Service.MemberService;
import ganggang3.gang.Service.MyplaceService;
import ganggang3.gang.Service.PlaceService;
import ganggang3.gang.domain.Member;
import ganggang3.gang.domain.Place;
import ganggang3.gang.dto.MyplaceDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
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
        List<MyplaceDto> myplaceDtoList=myplaceService.findMyplaceList(member);
        myplaceDtoList.forEach(p-> System.out.println(p.getName()));
        //then
        assertEquals(12,myplaceDtoList.size(),0);
    }
    //테스트 처음에 안되다가 계속 실행하면서 id가 올라가더니 10번 이후로 실행됨
    //이거 관련된 처리를 해야하는데 어캐할지 고민좀
    //test할때는 다른db에 저장되는게 맞는지 확인
    //콘솔상에서 확인안됨
    @Test
    @Rollback(false)
    public void addMyplace(){
        //given
        Member member = memberRepository.findById(1);
//        System.out.println(member.getName());
        Place place= placeRepository.findById(6).get();
//        System.out.println(place.getName());

        //when
        long id=myplaceService.add(member,place);
        System.out.println(id);
        //then
        List<MyplaceDto> myplaceList = myplaceService.findMyplaceList(member);
        MyplaceDto myplaceDto = myplaceList.get(0);

        assertEquals(place.getName(),myplaceDto.getName());
    }

    @Test
    public void deleteMyplace(){
        //given
//        Member member = memberRepository.findById(1);
//        Place place= placeRepository.findById(2).get();
//
//        //when
//        myplaceService.delete(member,place);
//
//        //then
//        myplaceService.find
    }

}
