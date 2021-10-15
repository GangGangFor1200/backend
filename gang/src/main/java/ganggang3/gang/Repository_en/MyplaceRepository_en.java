package ganggang3.gang.Repository_en;

import ganggang3.gang.domain.*;
import ganggang3.gang.domain_en.MyplaceEn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface MyplaceRepository_en extends JpaRepository<MyplaceEn,Long> {


    List<MyplaceEn> findAllByMember(Member member);
    MyplaceEn findByMemberAndName(Member member , String name);
    void deleteByMemberAndName(Member member, String name);

    Optional<MyplaceEn> findByName(String name);

    MyplaceEn findByIdAndMember(Long myplace_id, Member member);
}
