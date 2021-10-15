package ganggang3.gang.Service;

import ganggang3.gang.Repository.MyplaceRepository;
import ganggang3.gang.domain.*;
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

    public MyplaceEn findByMemberAndName(MemberEn member, String name){
        MyplaceEn myplace=myplaceRepository.findByMemberAndName(member,name);
        return myplace;
        //return myplace.orElseThrow(()->new NoSuchElementException("myplace가 존재하지 않습니다"));-> 이거 추가하면 아래에서 add되어 있는지 확인을 못함
    }

    @Transactional
    public Long add(MemberEn member, PlaceEn place){

        List<MyplaceCourse> myplace_courseList=new ArrayList<>();
           MyplaceEn myplace = MyplaceEn.createMyplace(
                   place.getName(),
                   place.getCategory().getName(),
                   place.getLocation_x(),
                   place.getLocation_y(),
                   place.getAddress(),
                   member
           );
        //add되어 있는지 확인
        MyplaceEn check = findByMemberAndName(member, place.getName());
        if (check!=null){
            //예외처리 하기
            throw new DatabaseException("이미 저장되어 있는 MYPLACE입니다!");
        }
        MyplaceEn saved = myplaceRepository.save(myplace);

       return saved.getId();
    }
    @Transactional
    public Long addFromApi(MemberEn member, Map<String,Object> map) {
        List<MyplaceEn> myplaceList=new ArrayList<>();
        MyplaceEn myplace = MyplaceEn.createMyplace(
                map.get("name").toString(),
                map.get("category").toString(),
                //double 인데?!!?!?!
                Double.valueOf(map.get("location_x").toString()),
                Double.valueOf(map.get("location_y").toString()),
                map.get("address").toString(),
                member
        );


        MyplaceEn check = findByMemberAndName(member, myplace.getName());
        if (check!=null){
            //예외처리 하기
            throw new DatabaseException("이미 저장되어 있는 MYPLACE입니다!");
        }
        MyplaceEn saved = myplaceRepository.save(myplace);

        return saved.getId();

    }

    @Transactional
    public void deleteByPlace(MemberEn member, PlaceEn place){//place로 지우기
        MyplaceEn rep = findByMemberAndName(member, place.getName());
        if (rep==null){
            //예외처리 하기
            throw new DatabaseException("저장되지 않는 PLACE입니다!");
        }
        myplaceRepository.deleteByMemberAndName(member, place.getName());
    }
    @Transactional
    public void deleteByMyplace(MemberEn member, MyplaceEn myplace){//myplace로 지우기
        myplaceRepository.deleteByMemberAndName(member, myplace.getName());
    }

    public List<MyplaceEn> findMyplaceList(MemberEn member){
        return myplaceRepository.findAllByMember(member);
    }
    public MyplaceEn findById(Long myplaceId) {
        Optional<MyplaceEn> myplace=myplaceRepository.findById(myplaceId);
        return myplace.orElseThrow(()->new NoSuchElementException("myplace가 존재하지 않습니다"));
    }

    public List<MyplaceEn> convertMyplaceList(List<Map<String,Object>> list, MemberEn member) {
        List<MyplaceEn> myplaceList=new ArrayList<>();

        for(int i=0;i<list.size();i++){
            Map<String,Object> map=list.get(i);
            MyplaceEn myplace=myplaceRepository.findByMemberAndName(member,map.get("name").toString());
            myplaceList.add(myplace);
        }
        return myplaceList;
    }


    public MyplaceEn findByIdAndMember(Long myplace_id, MemberEn member) {
        return myplaceRepository.findByIdAndMember(myplace_id,member);
    }

    public MyplaceEn findByName(String myplace_name) {
        Optional<MyplaceEn> byName = myplaceRepository.findByName(myplace_name);
        return byName.orElseThrow(()->new NoSuchElementException("myplace가 존재하지 않습니다"));
    }
}

