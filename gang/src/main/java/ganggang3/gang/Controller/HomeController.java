package ganggang3.gang.Controller;

import ganggang3.gang.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;
    @GetMapping("")
    public String writeIp() {
//        HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
//        String ip = req.getHeader("X-FORWARDED-FOR");
//        if (ip == null) {
//            ip = req.getRemoteAddr();
//        }
//        memberService.addMember(ip);
        return "index.html";
    }
}
