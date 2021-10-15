package ganggang3.gang.Api;

import ganggang3.gang.Service.CourseService;
import ganggang3.gang.Service.MemberService;
import ganggang3.gang.Service.MyplaceService;
import ganggang3.gang.domain.CourseEn;
import ganggang3.gang.domain.MemberEn;
import ganggang3.gang.domain.MyplaceEn;
import ganggang3.gang.dto.CourseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CourseApiController {
    private final CourseService courseService;
    private final MyplaceService myplaceService;
    private final MemberService memberService;

    @GetMapping("/api/course/findall/{memberid}")
    public Result findAllCourse(@PathVariable("memberid") Long member_id){
        MemberEn member = memberService.findById(member_id);
        List<CourseEn> courseList = courseService.findAllByMember(member);

        List<CourseDto> courseDtoList=new ArrayList<>();
        if (courseList!=null)
            courseList.forEach(
                    course -> {
                        CourseDto cd= CourseDto.of(course);
                        courseDtoList.add(cd);
                    }
            );

        return new Result(courseDtoList);
    }

    @PostMapping("/api/course/add/{memberid}")
    public void addCourse(@PathVariable("memberid") Long member_id,
                          @RequestBody Map<String,Object> map){
        MemberEn member= memberService.findById(member_id);

        String name=map.get("name").toString();

        //Map에서 받은 HashMapList를 Myplace로 변환
        List<MyplaceEn> myplaceList= myplaceService.convertMyplaceList((List<Map<String, Object>>) map.get("myplaceList"),member);

        courseService.addCourse(member,myplaceList,name);
    }
    @PutMapping("/api/course/update/{memberid}/{courseid}")
    public void updateCourse(@PathVariable("memberid") Long member_id,
                          @PathVariable("courseid") Long course_id,
                          @RequestBody Map<String,Object> map){
        MemberEn member= memberService.findById(member_id);
        Optional<CourseEn> course = courseService.findById(course_id);
        String name=map.get("name").toString();

        //Map에서 받은 HashMapList를 Myplace로 변환
        List<MyplaceEn> myplaceList= myplaceService.convertMyplaceList((List<Map<String, Object>>) map.get("myplaceList"),member);

        courseService.updateCourse(member,course.get(),myplaceList,name);
    }


    @DeleteMapping("/api/course/delete/{courseid}")
    public void deleteCourse(@PathVariable("courseid") Long course_id){
        courseService.deleteCourse(course_id);
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
