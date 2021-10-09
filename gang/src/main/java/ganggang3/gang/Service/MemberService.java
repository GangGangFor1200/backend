package ganggang3.gang.Service;

import ganggang3.gang.AuthorizationKakao;
import ganggang3.gang.Repository.MemberRepository;
import ganggang3.gang.domain.Member;
import ganggang3.gang.exception.DatabaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService  {

    private final KakaoService kakaoService;

    private final MemberRepository memberRepository;

    @Transactional
    public Map<String,String> oauth2AuthorizationKakao(String code) throws Exception {
        AuthorizationKakao authorization = kakaoService.callTokenApi(code);
        String userInfoFromKakao = kakaoService.callGetUserByAccessToken(authorization.getAccess_token());
        System.out.println("userInfoFromKakao = " + userInfoFromKakao);
        String [] arr=userInfoFromKakao.split(",");
        Long memberid= Long.parseLong(arr[0].substring(6));
        saveMember(memberid, authorization.getAccess_token());
        Map<String, String> map=new HashMap<>();
        map.put("memberid",""+memberid);
        map.put("accessToken",authorization.getAccess_token());
        return map;

    }
    @Transactional
    public void saveMember(Long id,String access_token){
        Member member=Member.createMember(id,access_token);
        memberRepository.save(member);
    }

    public Member findById(Long memberId){
        Optional<Member> member=memberRepository.findById(memberId);
        return member.orElseThrow(()->new NoSuchElementException("멤버가 존재하지 않습니다"));
    }

    public void delete(Long id) {
        Optional<Member> member=memberRepository.findById(id);
        Member ById=member.orElseThrow(()->new DatabaseException("member가 존재하지 않습니다"));
        memberRepository.delete(ById);
    }
//    public Member findByName(String name){
//        Optional<Member> member = memberRepository.findByName(name);
//        return member.orElseThrow(()->new NoSuchElementException("멤버가 존재하지 않습니다"));
//    }
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



}