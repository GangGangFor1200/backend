package ganggang3.gang.Repository;

import ganggang3.gang.domain.MemberEn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEn,Long> {
    Optional<MemberEn> findById(Long id);

    Optional<MemberEn> findByUsername(String member_name);

    List<MemberEn> findByUsernameAndPassword(String username, String password);
}
