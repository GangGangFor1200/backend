package ganggang3.gang.Service;

import ganggang3.gang.Repository.MyplaceRepository;
import ganggang3.gang.Repository.PlaceRepository;
import ganggang3.gang.domain.Member;
import ganggang3.gang.domain.Myplace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
