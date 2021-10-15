package ganggang3.gang.Repository_en;

import ganggang3.gang.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository_en extends JpaRepository<Member,Long> {
    Optional<Member> findById(Long id);

    Optional<Member> findByUsername(String member_name);

    List<Member> findByUsernameAndPassword(String username, String password);
}
