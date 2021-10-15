package ganggang3.gang.dto;

import ganggang3.gang.domain.MyplaceCourse;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
//프론트에서 코스 선택했을 때 관련된 마이플레이스 보여주는 것
public class MyplaceCourseDto {
    private Long id;
    private String courseName;
    //마이플레이스 선택했을 때 페이지에 지도띄워야해서 이게 필요
    private MyplaceDto myplaceDto;


    public static MyplaceCourseDto of (MyplaceCourse mc, String name){
        return new MyplaceCourseDto(
                mc.getId(),
                name,
                MyplaceDto.of(mc.getMyplace())
        );
    }
}
