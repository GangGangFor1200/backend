package ganggang3.gang.Api;

import ganggang3.gang.Service.KakaoService;
import ganggang3.gang.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;
    private final KakaoService kakaoService;


    //인가코드 받아서 멤버 아이디 보내기
    @GetMapping("/api/oauth2/authorization/kakao")
    public Map<String,String> oauth2AuthorizationKakao(@RequestParam("code") String code) throws Exception {
        return memberService.oauth2AuthorizationKakao(code);
    }

    @GetMapping("/api/kakaoLogout")
    public void kakaoLogout(@RequestParam("memberid") String member_id) throws Exception {
        memberService.delete(member_id);
        kakaoService.logoutByAccessToken(member_id);
    }
}
