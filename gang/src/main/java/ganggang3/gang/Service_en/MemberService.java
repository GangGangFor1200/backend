package ganggang3.gang.Service_en;
import ganggang3.gang.Repository.MemberRepository;
import ganggang3.gang.domain.MemberEn;
import ganggang3.gang.exception.DatabaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService  {

    private final KakaoService kakaoService;

    private final MemberRepository memberRepository;

//    @Transactional
//    public Map<String,String> oauth2AuthorizationKakao(String code) {
//        AuthorizationKakao authorization = kakaoService.callTokenApi(code);
//        String userInfoFromKakao = kakaoService.callGetUserByAccessToken(authorization.getAccess_token());
//        String [] arr=userInfoFromKakao.split(",");
//        Long memberid= Long.parseLong(arr[0].substring(6));
//        Optional<Member> byId = memberRepository.findById(memberid);
//        if (byId.isEmpty())
//            saveMember(memberid,authorization.getAccess_token());
//        else
//            byId.get().setAccess_token(authorization.getAccess_token());
//        Map<String, String> map=new HashMap<>();
//        map.put("memberid",""+memberid);
//        map.put("access_token",authorization.getAccess_token());
//        return map;
//
//    }

    @Transactional
    public void saveMember(String username,String password){
        MemberEn member= MemberEn.createMember(username,password);
        memberRepository.save(member);
    }

    public MemberEn findById(Long member_id){
        Optional<MemberEn> member=memberRepository.findById(member_id);
        return member.orElseThrow(()->new NoSuchElementException("멤버가 존재하지 않습니다"));
    }

    public void delete(Long member_id) {
        Optional<MemberEn> member=memberRepository.findById(member_id);
        MemberEn ById=member.orElseThrow(()->new DatabaseException("member가 존재하지 않습니다"));
        memberRepository.delete(ById);
    }

    public MemberEn findByUsernameAndPassword(String username, String password){
        List<MemberEn> byUsernameAndPassword = memberRepository.findByUsernameAndPassword(username, password);
        Optional<MemberEn> Optionalmember= Optional.ofNullable(byUsernameAndPassword.get(0));
        MemberEn member=Optionalmember.orElseThrow(()->new DatabaseException("가입된 회원이 아닙니다!"));
        return member;
    }

    public MemberEn findByUsername(String member_name) {
        Optional<MemberEn> byUsername = memberRepository.findByUsername(member_name);
        return byUsername.orElseThrow(()->new DatabaseException("member가 존재하지 않습니다"));
    }
}