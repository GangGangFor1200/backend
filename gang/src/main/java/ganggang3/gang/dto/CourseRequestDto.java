package ganggang3.gang.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ganggang3.gang.domain.Myplace;
import ganggang3.gang.domain.Province;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CourseRequestDto {

    private String name;

    private List<Myplace> myplaceList;

    public static CourseRequestDto of (String name, List<Myplace> myplaceList){
        return new CourseRequestDto(
                name,
                myplaceList
        );
    }
}
