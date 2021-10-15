package ganggang3.gang.Api;

import ganggang3.gang.Service.KakaoService;
import ganggang3.gang.Service.MemberService;
import ganggang3.gang.domain.MemberEn;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;
    private final KakaoService kakaoService;

    //회원가입
    @PostMapping("/api/member/add")
    public void addMember(@RequestBody Map<String,String> map){
        String username=map.get("username");
        String password=map.get("password");
        memberService.saveMember(username,password);
    }
    //로그인
    @PostMapping("/api/member/login")
    public Map<String,String> findMember(@RequestBody Map<String,String> map){
        String username=map.get("username");
        String password=map.get("password");
        MemberEn member=memberService.findByUsernameAndPassword(username,password);
        Map<String,String> map1=new HashMap<>();
        map1.put("memberid",member.getId().toString());
        System.out.println(map1);
        return map1;
    }


    //인가코드 받아서 멤버 아이디 보내기
//    @GetMapping("/api/oauth2/authorization/kakao")
//    public Map<String,String> oauth2AuthorizationKakao(@RequestParam("code") String code){
//        return memberService.oauth2AuthorizationKakao(code);
//    }

    //로그인 해제
//    @GetMapping("/api/kakaologout")
//    public void kakaoLogout(@RequestParam("memberid") Long member_id) {
//        String access_token=memberService.findById(member_id).getAccess_token();
//        kakaoService.logoutByAccessToken(access_token);
//    }
}
