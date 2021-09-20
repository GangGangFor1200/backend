package ganggang3.gang.Service;

import ganggang3.gang.Repository.PlaceRepository;
import ganggang3.gang.domain.Member;
import ganggang3.gang.domain.Place;
import ganggang3.gang.dto.MyplaceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

@Component
public class add implements ApplicationRunner {
    @Autowired
    MemberService memberService;
    @Autowired
    MyplaceService myplaceService;
    @Autowired
    PlaceRepository placeRepository;
    @Autowired
    MemberService memberRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("ApplicationRunner!");


//        long memberId=1;
//
//        Member member=memberService.findById(memberId);
//        System.out.println(member.getName()+" "+member.getId());
//
//        //when
//        List<MyplaceDto> myplaceDtoList=myplaceService.findMyplaceList(member);
//        myplaceDtoList.forEach(p-> System.out.println(p.getName()));
//        //then
////        assertEquals(10,myplaceDtoList.size(),0);


        Place place= placeRepository.findById(1).get();
        System.out.println(place.getName());
        Member member = memberRepository.findById(2);
        System.out.println(member.getName());
        //when
        long id=myplaceService.add(member,place);
        System.out.println(id);
        //then
        List<MyplaceDto> myplaceList = myplaceService.findMyplaceList(member);
        MyplaceDto myplaceDto = myplaceList.get(0);
//        assertEquals(place.getName(),myplaceDto.getName());
        System.out.println(myplaceDto.getName());




    }


}
