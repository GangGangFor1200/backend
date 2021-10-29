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

    public Myplace findByMemberAndPlaceId(Member member, Long placeid){
        Myplace myplace= myplaceRepository.findByMemberAndPlaceId(member,placeid);
        return myplace;
    }



    @Transactional
    public Long add(Member member, Place place){

        List<MyplaceCourse> myplace_courseList=new ArrayList<>();
           Myplace myplace = Myplace.createMyplace(
                   place.getName(),
                   place.getCategory().getName(),
                   place.getLocation_x(),
                   place.getLocation_y(),
                   place.getAddress(),
                   place.getId(),
                   member
           );
        //add되어 있는지 확인
        Myplace check = findByMemberAndName(member, place.getName());
        if (check!=null){
            //예외처리 하기
            throw new DatabaseException("이미 저장되어 있는 MYPLACE입니다!");
        }
        Myplace saved = myplaceRepository.save(myplace);

       return saved.getId();
    }
    @Transactional
    public Myplace addFromApi(Member member, Map<String,Object> map) {
        List<Myplace> myplaceList=new ArrayList<>();

        Myplace myplace = Myplace.createMyplace(
                map.get("name").toString(),
                map.get("category").toString(),
                Double.valueOf(map.get("location_x").toString()),
                Double.valueOf(map.get("location_y").toString()),
                map.get("address").toString(),
                Long.valueOf(map.get("placeId").toString()),
                member
        );
        //api에서 검사를 하고 service를 호출해서 2번 검사할필요없다
        // 프론트에서 이미 저장되어있는 myplace를 넘겨받아야해서 오류메세지 못보여주고 api단에서 검사했음
        Myplace check = findByMemberAndPlaceId(member, myplace.getPlaceId());

        Myplace saved;

        if (check!=null){
            saved= check;
        }
        else{
            saved =  myplaceRepository.save(myplace);
        }

        return saved;
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
    public Myplace findById(Long myplaceId) {
        Optional<Myplace> myplace=myplaceRepository.findById(myplaceId);
        return myplace.orElseThrow(()->new NoSuchElementException("myplace가 존재하지 않습니다"));
    }

    public List<Myplace> convertMyplaceList(List<Map<String,Object>> list, Member member) {
        List<Myplace> myplaceList=new ArrayList<>();

        for(int i=0;i<list.size();i++){
            Map<String,Object> map=list.get(i);
            Myplace myplace=myplaceRepository.findByMemberAndName(member,map.get("name").toString());
            myplaceList.add(myplace);
        }
        return myplaceList;
    }


    public Myplace findByIdAndMember(Long myplace_id, Member member) {
        return myplaceRepository.findByIdAndMember(myplace_id,member);
    }

    public Myplace findByName(String myplace_name) {
        Optional<Myplace> byName = myplaceRepository.findByName(myplace_name);
        return byName.orElseThrow(()->new NoSuchElementException("myplace가 존재하지 않습니다"));
    }
}

