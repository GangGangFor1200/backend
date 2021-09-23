package ganggang3.gang.Service;

import ganggang3.gang.Repository.MyplaceRepository;
import ganggang3.gang.domain.Member;
import ganggang3.gang.domain.Myplace;
import ganggang3.gang.domain.MyplaceCourse;
import ganggang3.gang.domain.Place;
import ganggang3.gang.dto.MyplaceDto;
import ganggang3.gang.exception.DatabaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Myplace check = findByMemberAndName(member, place.getName());
        if (check!=null){
            //예외처리 하기
            throw new DatabaseException("이미 저장되어 있는 PLACE입니다!");
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
    public void deleteByPlace(Member member, Place place){//place로 지우기
        Myplace rep = findByMemberAndName(member, place.getName());
        if (rep==null){
            //예외처리 하기
            throw new DatabaseException("저장되지 않는 PLACE입니다!");
        }
        myplaceRepository.deleteByMemberAndName(member, place.getName());
    }
    @Transactional
    public void deleteByMyplace(Member member, Myplace myplace){//myplace로 지우기
        myplaceRepository.deleteByMemberAndName(member, myplace.getName());
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

    public Myplace findById(long myplaceId) {
        return myplaceRepository.findById(myplaceId).get();
    }
}
