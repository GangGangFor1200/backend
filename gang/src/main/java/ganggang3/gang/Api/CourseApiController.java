package ganggang3.gang.Api;

import com.sun.istack.NotNull;
import ganggang3.gang.Service.CourseService;
import ganggang3.gang.Service.MemberService;
import ganggang3.gang.Service.MyplaceService;
import ganggang3.gang.domain.Member;
import ganggang3.gang.domain.Myplace;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CourseApiController {
    private final CourseService courseService;
    private final MyplaceService myplaceService;
    private final MemberService memberService;

    @PostMapping("/api/course/add/{member}")
    public void addCourse(@PathVariable("member") Long member_id,
                        @RequestBody @Valid addCourseRequest request){
        Member member= memberService.findById(member_id);
        courseService.addCourse(member,request.getMyplaceList(),request.getName());


    }

    @Data
    static class addCourseRequest{
        @NotNull
        private List<Myplace> myplaceList;
        @NotNull
        String name;
    }

//    @PutMapping("/api/v2/members/{id}")
//    public UpdateMemberResponse updateMemberV2(@PathVariable("id") Long id,
//                                               @RequestBody @Valid UpdateMemberRequest request) {
//        memberService.update(id, request.getName());
//        Member findMember = memberService.findOne(id);
//        return new UpdateMemberResponse(findMember.getId(), findMember.getName());
//    }
//@ToString
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class UserDto {
//
//    @NotNull
//    private String name;
//
//    @Email
//    private String email;
//}


}
