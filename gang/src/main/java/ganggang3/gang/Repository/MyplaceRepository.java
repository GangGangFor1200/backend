package ganggang3.gang.Repository;

import ganggang3.gang.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface MyplaceRepository  extends JpaRepository<MyplaceEn,Long> {


    List<MyplaceEn> findAllByMember(MemberEn member);
    MyplaceEn findByMemberAndName(MemberEn member , String name);
    void deleteByMemberAndName(MemberEn member, String name);

    Optional<MyplaceEn> findByName(String name);

    MyplaceEn findByIdAndMember(Long myplace_id, MemberEn member);
}
