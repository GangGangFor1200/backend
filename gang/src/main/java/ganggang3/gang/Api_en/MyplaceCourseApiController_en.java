package ganggang3.gang.Api_en;

import ganggang3.gang.Service.CourseService;
import ganggang3.gang.Service.MyplaceCourseService;
import ganggang3.gang.Service_en.CourseService_en;
import ganggang3.gang.Service_en.MyplaceCourseService_en;
import ganggang3.gang.domain.Course;
import ganggang3.gang.domain.MyplaceCourse;
import ganggang3.gang.domain_en.CourseEn;
import ganggang3.gang.domain_en.MyplaceCourseEn;
import ganggang3.gang.dto.MyplaceCourseDto;
import ganggang3.gang.dto.MyplaceDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MyplaceCourseApiController_en {
    //코스안에있는 모든 장소들 보여주는api
    private final MyplaceCourseService_en MyplaceCourseService;
    private final CourseService_en courseService;

    @GetMapping("/api/en/myplacecourse/findall/{courseid}")
    public Result findMyplaceCourseAllByCourse(@PathVariable("courseid") long course_id){
        Optional<CourseEn> course = courseService.findById(course_id);
        List<MyplaceCourseEn> allByCourse = MyplaceCourseService.findAllByCourse(course.get());
        List<MyplaceCourseDto> myplaceCourseDtoList =new ArrayList<>();

        if (allByCourse!=null) {
            allByCourse.forEach(mc -> {
                MyplaceCourseDto myplaceCourseDto = new MyplaceCourseDto(
                        mc.getId(),
                        course.get().getName(),
                        new MyplaceDto(
                                mc.getMyplace().getId(),
                                mc.getMyplace().getName(),
                                mc.getMyplace().getCategory(),
                                mc.getMyplace().getLocation_x(),
                                mc.getMyplace().getLocation_y(),
                                mc.getMyplace().getAddress()
                        )
                );
                myplaceCourseDtoList.add(myplaceCourseDto);
            });
        }

        return new Result(myplaceCourseDtoList);
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }

}
