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
       System.out.println(myplace.toString());
       Myplace saved = myplaceRepository.save(myplace);
        System.out.println(saved.toString());
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
