package ganggang3.gang.Service;

import ganggang3.gang.Repository.MemberRepository;
import ganggang3.gang.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService  {


    private final MemberRepository memberRepository;

    public Member findById(long memberId){
        Optional<Member> member=memberRepository.findById(memberId);
        return member.orElseThrow(()->new NoSuchElementException("멤버가 존재하지 않습니다"));
    }

//    @Transactional
//    public Member createMember(String name,String password){
//        Member member=new Member();
//        member.setName(name);
//        member.setPassword(passwordEncoder.encode(password));//encoding하여 password저장
//        return memberRepository.save(member);
//    }
//
//    //로그인 할 때 입력한 정보가 여기서 들어옴
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<Member> Loginmember=memberRepository.findByName(username);
//        Member member = Loginmember.orElseThrow(() -> new UsernameNotFoundException(username));
//        return new User(member.getName(),member.getPassword(),authorities());
//    }

//    private Collection<? extends GrantedAuthority> authorities() {
//        //ROLE_USER라는 권한을 리턴
//        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
//    }

    public Member findByName(String name) {
        Optional<Member> member=memberRepository.findByName(name);
        return member.orElseThrow(()->new NoSuchElementException("멤버가 존재하지 않습니다"));
    }

}