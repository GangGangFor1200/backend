package ganggang3.gang.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import ganggang3.gang.domain.Myplace;
import ganggang3.gang.domain.Province;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class CourseRequestDto {

    @JsonProperty("name")
    private String name;

    @JsonProperty("myplaceList")
    private List<Myplace> myplaceList;

    public static CourseRequestDto of (String name, List<Myplace> myplaceList){
        List<CourseInMyplaceDto> courseInMyplaceDtoList=new ArrayList<>();
        for(Myplace m:myplaceList){
            courseInMyplaceDtoList.add(new CourseInMyplaceDto(m.getId(),m.getName(),m.getCategory(),m.getLocation_x(),m.getLocation_y()));
        }
        return new CourseRequestDto(
                name,
                myplaceList
        );
    }
    @Data
    @AllArgsConstructor
    static
    class CourseInMyplaceDto{
        private Long id;

        private String name;

        private String category;

        private double location_x;
        private double location_y;
    }
}
