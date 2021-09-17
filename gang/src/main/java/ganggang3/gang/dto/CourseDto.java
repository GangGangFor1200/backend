package ganggang3.gang.dto;

import ganggang3.gang.domain.Course;
import ganggang3.gang.domain.Member;
import ganggang3.gang.domain.MyplaceCourse;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CourseDto {

    private String name;
    private Member member;
    private List<MyplaceCourse> myplace_courseList;

    public static CourseDto of (Course C, List<MyplaceCourse> myplace_courseList){
        return new CourseDto(
                C.getName(),
                C.getMember(),
                myplace_courseList
        );
    }
}
