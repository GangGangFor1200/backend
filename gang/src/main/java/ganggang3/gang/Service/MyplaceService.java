package ganggang3.gang.Service;

import ganggang3.gang.Repository.MyplaceRepository;
import ganggang3.gang.domain.Member;
import ganggang3.gang.domain.Myplace;
import ganggang3.gang.domain.MyplaceCourse;
import ganggang3.gang.domain.Place;
import ganggang3.gang.dto.MyplaceDto;
import ganggang3.gang.dto.PlaceDto;
import ganggang3.gang.dto.VlogDto;
import ganggang3.gang.exception.NoDeliveryException;
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
    public Myplace findByMemberAndName(Member member, String name){

        return myplaceRepository.findByMemberAndName(member,name);
    }

    @Transactional
    public Long add(Member member,Place place){
        //add되어 있는지 확인
        Myplace rep = findByMemberAndName(member, place.getName());
        if (rep!=null){
            //예외처리 하기
            throw new NoDeliveryException("이미 저장되어 있는 PLACE입니다!");
        }
        List<MyplaceCourse> myplace_courseList=new ArrayList<>();
           Myplace myplace = Myplace.createMyplace(
                   place.getName(),
                   place.getCategory().getName(),
                   place.getLocation_x(),
                   place.getLocation_y(),
                   place.getAddress(),
                   member
           );

        Myplace saved = myplaceRepository.save(myplace);

       return saved.getId();
    }
    @Transactional
    public void delete(Member member, Place place){
        Myplace rep = findByMemberAndName(member, place.getName());
        if (rep==null){
            //예외처리 하기
            throw new NoDeliveryException("저장되지 않는 PLACE입니다!");
        }
        myplaceRepository.deleteByMemberAndName(member, place.getName());
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
