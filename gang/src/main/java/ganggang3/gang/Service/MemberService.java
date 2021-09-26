package ganggang3.gang.Service;

import ganggang3.gang.Repository.MemberRepository;
import ganggang3.gang.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member findById(long memberId){
        Optional<Member> member=memberRepository.findById(memberId);
        return member.orElseThrow(()->new NoSuchElementException("멤버가 존재하지 않습니다"));
    }
}
