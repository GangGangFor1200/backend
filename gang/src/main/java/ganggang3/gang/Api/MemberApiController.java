package ganggang3.gang.Api;

import ganggang3.gang.Service.KakaoService;
import ganggang3.gang.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;
    private final KakaoService kakaoService;


    //인가코드 받아서 멤버 아이디 보내기
    @GetMapping("/api/oauth2/authorization/kakao")
    public Map<String,String> oauth2AuthorizationKakao(@RequestParam("code") String code){
        return memberService.oauth2AuthorizationKakao(code);
    }

    //로그인 해제
    @GetMapping("/api/kakaologout")
    public void kakaoLogout(@RequestParam("memberid") Long member_id) {
        String access_token=memberService.findById(member_id).getAccess_token();
        kakaoService.logoutByAccessToken(access_token);
    }
}
