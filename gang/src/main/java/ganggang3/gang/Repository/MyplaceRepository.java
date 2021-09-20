package ganggang3.gang.Repository;

import ganggang3.gang.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface MyplaceRepository  extends JpaRepository<Myplace,Long> {


    List<Myplace> findAllByMember(Member member);
    Myplace findAllByMemberAndName(Member member , String name);


}
