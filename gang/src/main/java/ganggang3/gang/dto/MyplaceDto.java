package ganggang3.gang.dto;

import ganggang3.gang.domain.Myplace;
import ganggang3.gang.domain.MyplaceCourse;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MyplaceDto {

    private String name;
    private String category;
    private double location_x;
    private double location_y;
    private String address;
    private List<MyplaceCourse> myplace_courseList;

    public static MyplaceDto of (Myplace M, List<MyplaceCourse> myplace_courseList){
        return new MyplaceDto(
                M.getName(),
                M.getCategory(),
                M.getLocation_x(),
                M.getLocation_y(),
                M.getAddress(),
                myplace_courseList
        );
    }
}
