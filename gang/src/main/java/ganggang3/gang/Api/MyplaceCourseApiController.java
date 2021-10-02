package ganggang3.gang.Api;

import ganggang3.gang.Repository.CourseRepository;
import ganggang3.gang.Repository.MyplaceCourseRepository;
import ganggang3.gang.Service.CourseService;
import ganggang3.gang.Service.MyplaceCourseService;
import ganggang3.gang.Service.MyplaceService;
import ganggang3.gang.domain.Course;
import ganggang3.gang.domain.MyplaceCourse;
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
public class MyplaceCourseApiController {
    //코스안에있는 모든 장소들 보여주는api
    private final ganggang3.gang.Service.MyplaceCourseService MyplaceCourseService;
    private final CourseService courseService;

    @GetMapping("/api/myplacecourse/findall/{courseid}")
    public Result findMyplaceCourseAllByCourse(@PathVariable("courseid") long course_id){
        Optional<Course> course = courseService.findById(course_id);
        List<MyplaceCourse> allByCourse = MyplaceCourseService.findAllByCourse(course.get());
        List<MyplaceCourseDto> myplaceCourseDtoList =new ArrayList<>();

        allByCourse.forEach(mc->{
            MyplaceCourseDto myplaceCourseDto = MyplaceCourseDto.of(mc,course.get().getName());
            myplaceCourseDtoList.add(myplaceCourseDto);
        });

        return new Result(myplaceCourseDtoList);
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }

}
