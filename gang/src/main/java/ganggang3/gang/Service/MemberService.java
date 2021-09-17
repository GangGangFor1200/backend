package ganggang3.gang.Service;

import ganggang3.gang.Repository.MemberRepository;
import ganggang3.gang.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member findById(long memberId){
        return memberRepository.findById(memberId);
    }
}
