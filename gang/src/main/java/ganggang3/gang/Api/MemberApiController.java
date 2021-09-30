package ganggang3.gang.Api;

import ganggang3.gang.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

//    @PostMapping("/api/savemember")
//    public String signup(@RequestBody Map<String,String> map) { // 회원 추가
//        memberService.createMember(map.get("username"),map.get("password"));
//        return "redirect:/login";
//    }
//    @PostMapping("/api/login")
//    public String login(@RequestBody Map<String,String> map) { // 회원 추가
//        memberService.loadUserByUsername(map.get("username"));
//        return "redirect:/login";
//    }
}
