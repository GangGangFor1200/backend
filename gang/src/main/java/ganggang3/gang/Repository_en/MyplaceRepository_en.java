package ganggang3.gang.Repository_en;

import ganggang3.gang.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface MyplaceRepository_en extends JpaRepository<Myplace,Long> {


    List<Myplace> findAllByMember(Member member);
    Myplace findByMemberAndName(Member member , String name);
    void deleteByMemberAndName(Member member, String name);

    Optional<Myplace> findByName(String name);

    Myplace findByIdAndMember(Long myplace_id, Member member);
}
