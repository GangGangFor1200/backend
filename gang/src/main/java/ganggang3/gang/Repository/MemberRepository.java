package ganggang3.gang.Repository;

import ganggang3.gang.domain.Member;
import ganggang3.gang.domain.Myplace;

public interface MemberRepository {

    Member findById(long id);
}
