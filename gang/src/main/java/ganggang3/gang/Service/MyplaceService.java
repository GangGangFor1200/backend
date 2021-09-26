package ganggang3.gang.Service;

import ganggang3.gang.Repository.MyplaceRepository;
import ganggang3.gang.domain.*;
import ganggang3.gang.dto.MyplaceDto;
import ganggang3.gang.exception.DatabaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MyplaceService {

    private final MyplaceRepository myplaceRepository;
    public Myplace findByMemberAndName(Member member, String name){
        Optional<Myplace> myplace=myplaceRepository.findByMemberAndName(member,name);
        return myplace.orElseThrow(()->new NoSuchElementException("myplace가 존재하지 않습니다"));
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

    public List<Myplace> findMyplaceList(Member member){
        return myplaceRepository.findAllByMember(member);
    }
    public Myplace findById(long myplaceId) {
        return myplaceRepository.findById(myplaceId).get();
    }

}

