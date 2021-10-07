package ganggang3.gang.Api;

import ganggang3.gang.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    //인가코드 받아서 멤버 아이디 보내기
    @GetMapping("/oauth2/authorization/kakao")
    public Map<String,String> oauth2AuthorizationKakao(@RequestParam("code") String code) throws Exception {
        Long memberid=memberService.oauth2AuthorizationKakao(code);
        Map<String,String> map=new HashMap<>();
        map.put("memberid",""+memberid);
        return map;
    }
}
