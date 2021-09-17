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

    public List<Myplace> findMyplaceList(Member member){
        return myplaceRepository.findAllByMember(member);
    }
    public Long add(Myplace myplace){
        myplaceRepository.save(myplace);
        Optional<Myplace> byId = myplaceRepository.findById(myplace.getId());
        return byId.get().getId();
    }
    public List<MyplaceDto> ApifindMyplaceList(Member member){
        List<Myplace> myplaceList=findMyplaceList(member);
        List<MyplaceDto> myplaceDtoList = new ArrayList<>();
        myplaceList.forEach(p->{
            List<MyplaceCourse> myplaceCourseList=p.getMyplace_courseList();
            MyplaceDto md=MyplaceDto.of(p,myplaceCourseList);
            myplaceDtoList.add(md);
        });

        return myplaceDtoList;
    }

}
