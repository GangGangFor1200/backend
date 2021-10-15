package ganggang3.gang.dto;

import ganggang3.gang.domain.Course;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourseDto {

    private long courseid;
    private String name;
    private long memberid;

    public static CourseDto of (Course C){
        return new CourseDto(
                C.getId(),
                C.getName(),
                C.getMember().getId()
        );
    }
}
