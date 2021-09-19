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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MyplaceService {

    private final MyplaceRepository myplaceRepository;



    public Long add(Myplace myplace){
        //여기서 반환한거 아이디 그냥 반환해도 될거같은데 영한이형은 jpa사용안해서 지금 한 방식대로 한건가 싶기두 하고 일단은 이렇게 두고 나중에 다시 보는걸로
        myplaceRepository.save(myplace);
        Optional<Myplace> byId = myplaceRepository.findById(myplace.getId());
        return byId.get().getId();
    }
    //주리 대답오면 위에꺼 아래꺼에 합치기
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
