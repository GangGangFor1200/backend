//package ganggang3.gang;
//
//import ganggang3.gang.Repository.PlaceRepository;
//import ganggang3.gang.Service.MemberService;
//import ganggang3.gang.Service.MyplaceService;
//import ganggang3.gang.domain.Member;
//import ganggang3.gang.domain.Place;
//import ganggang3.gang.dto.MyplaceDto;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//
//@Component
//public class add implements ApplicationRunner {
//    @Autowired
//    MemberService memberService;
//    @Autowired
//    MyplaceService myplaceService;
//    @Autowired
//    PlaceRepository placeRepository;
//    @Autowired
//    MemberService memberRepository;
//
//    @Override
//    @Transactional
//    public void run(ApplicationArguments args) throws Exception {
//        System.out.println("Myplace data 넣음!");
//
//        for (int i=0; i<5;i++) {
//            Member member = memberRepository.findById((i+1L));
//            System.out.println(member.getName());
//            Place place = placeRepository.findById(1L).get();
//            System.out.println(place.getName());
//            long id = myplaceService.add(member, place);
//            System.out.println(id);
//        }
//        for (int i=0; i<4;i++) {
//            Member member = memberRepository.findById((i+1L));
//            System.out.println(member.getName());
//            Place place = placeRepository.findById((i+2L)).get();
//            System.out.println(place.getName());
//            long id = myplaceService.add(member, place);
//            System.out.println(id);
//        }
//
//    }
//
//}
