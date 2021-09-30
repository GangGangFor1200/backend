package ganggang3.gang.Service;

import ganggang3.gang.Repository.MyplaceRepository;
import ganggang3.gang.domain.*;
import ganggang3.gang.dto.MyplaceDto;
import ganggang3.gang.exception.DatabaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MyplaceService {

    private final MyplaceRepository myplaceRepository;
    public Myplace findByMemberAndName(Member member, String name){
        Myplace myplace=myplaceRepository.findByMemberAndName(member,name);
        return myplace;
        //return myplace.orElseThrow(()->new NoSuchElementException("myplace가 존재하지 않습니다"));-> 이거 추가하면 아래에서 add되어 있는지 확인을 못함
    }

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
        //add되어 있는지 확인
        Myplace check = findByMemberAndName(member, place.getName());
        if (check!=null){
            //예외처리 하기
            throw new DatabaseException("이미 저장되어 있는 PLACE입니다!");
        }
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

    public List<Myplace> convertMyplace(List<Map<String,Object>> list,Member member) {
        List<Myplace> myplaceList=new ArrayList<>();

        for(int i=0;i<list.size();i++){
            Map<String,Object> map=list.get(i);
            Myplace myplace=myplaceRepository.findByMemberAndName(member,map.get("name").toString());
            myplaceList.add(myplace);
        }
        return myplaceList;
    }
}

