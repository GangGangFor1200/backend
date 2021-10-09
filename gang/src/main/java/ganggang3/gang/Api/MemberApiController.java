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
        Long memberid=memberService.oauth2AuthorizationKakao(code);
        Map<String,String> map=new HashMap<>();
        map.put("memberid",""+memberid);
        return map;
    }

    @GetMapping("/api/kakaoLogout")
    public void kakaoLogout(@RequestParam("id") Long id) throws Exception {
        String access_token=memberService.findById(id).getAccess_token();
        memberService.delete(id);
        kakaoService.logoutByAccessToken(access_token);
    }
}
