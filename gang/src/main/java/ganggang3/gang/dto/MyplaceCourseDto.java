package ganggang3.gang.dto;

import ganggang3.gang.domain.MyplaceCourse;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MyplaceCourseDto {
    private Long id;
    private String courseName;
    private MyplaceDto myplaceDto;


    public static MyplaceCourseDto of (MyplaceCourse mc,String name){
        return new MyplaceCourseDto(
                mc.getId(),
                name,
                MyplaceDto.of(mc.getMyplace())
        );
    }
}
