package ganggang3.gang.Service;

import ganggang3.gang.Repository.MyplaceRepository;
import ganggang3.gang.domain.Member;
import ganggang3.gang.domain.Myplace;
import ganggang3.gang.domain.MyplaceCourse;
import ganggang3.gang.domain.Place;
import ganggang3.gang.dto.MyplaceDto;
import ganggang3.gang.dto.PlaceDto;
import ganggang3.gang.dto.VlogDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MyplaceService {

    private final MyplaceRepository myplaceRepository;

    //기존에 있는거 처리하는거 추가해야하나?
    @Transactional
    public Long add(Member member,Place place){
    List<MyplaceCourse> myplace_courseList=new ArrayList<>();
       Myplace myplace = Myplace.createMyplace(
               place.getName(),
               place.getCategory().getName(),
               place.getLocation_x(),
               place.getLocation_y(),
               place.getAddress(),
               member
       );

        Myplace chk = myplaceRepository.findAllByMemberAndName(member, myplace.getName());
        // 해당 member에 이미 myplace가 있다면 에러 발생
        if (chk!=null){
            //에로 발생시키기
            //exception으로 만들어야함!!!
            System.out.println("중복된 Myplace입니다");
            return chk.getId();

        }

        Myplace saved = myplaceRepository.save(myplace);

       return saved.getId();
    }


    public List<MyplaceDto> findMyplaceList(Member member){
        List<Myplace> myplaceList = myplaceRepository.findAllByMember(member);
        List<MyplaceDto> myplaceDtoList = new ArrayList<>();
        if (myplaceList!=null) {
            myplaceList.forEach(p -> {
                List<MyplaceCourse> myplaceCourseList = p.getMyplace_courseList();
                MyplaceDto md = MyplaceDto.of(p, myplaceCourseList);
                myplaceDtoList.add(md);
            });
        }

        return myplaceDtoList;
    }

}
