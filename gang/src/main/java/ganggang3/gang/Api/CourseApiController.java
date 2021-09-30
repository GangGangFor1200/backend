package ganggang3.gang.Api;

import com.sun.istack.NotNull;
import ganggang3.gang.Service.CourseService;
import ganggang3.gang.Service.MemberService;
import ganggang3.gang.Service.MyplaceService;
import ganggang3.gang.domain.Course;
import ganggang3.gang.domain.Member;
import ganggang3.gang.domain.Myplace;
import ganggang3.gang.dto.CourseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CourseApiController {
    private final CourseService courseService;
    private final MyplaceService myplaceService;
    private final MemberService memberService;

    @PostMapping("/api/course/add/{member}")
    public void addCourse(@PathVariable("member") Long member_id,
                          @RequestBody Map<String,Object> map){
        Member member= memberService.findById(member_id);

        String name=map.get("name").toString();

        List<Myplace> myplaceList= myplaceService.convertMyplace((List<Map<String, Object>>) map.get("myplaceList"),member);

        courseService.addCourse(member,myplaceList,name);
    }
//    @PutMapping("api/course/update/{member}/")
//    public Long updateCourse()


    @GetMapping("/api/course/findAll/{member}")
    public Result findAllByMember(@PathVariable("member") Long member_id){
        Member member = memberService.findById(member_id);
        List<Course> allByMember = courseService.findAllByMember(member);

        List<CourseDto> courseDtoList=new ArrayList<>();

        if (allByMember!=null)
        allByMember.forEach(
                course -> {
                    CourseDto cd= CourseDto.of(course);
                    courseDtoList.add(cd);
                }
        );

        return new Result(courseDtoList);
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }

    //참고용


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
