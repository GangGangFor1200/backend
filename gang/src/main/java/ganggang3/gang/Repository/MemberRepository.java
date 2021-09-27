package ganggang3.gang.Repository;

import ganggang3.gang.domain.Member;
import ganggang3.gang.domain.Myplace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findById(long id);

    Optional<Member> findByName(String username);
}
