package ganggang3.gang.Repository;

import ganggang3.gang.domain.Member;
import ganggang3.gang.domain.Myplace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Member findById(long id);

}
