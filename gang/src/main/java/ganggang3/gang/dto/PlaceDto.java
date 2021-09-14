package ganggang3.gang.dto;


import ganggang3.gang.domain.Place;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PlaceDto {
    private String name;
    private double location_x;
    private double location_y;
    private String explanation;
    private String address;
    private List<VlogDto> vlog_list;



    public static PlaceDto of (Place P, List<VlogDto> vlogList){
        return new PlaceDto(
                P.getName(),
                P.getLocation_x(),
                P.getLocation_y(),
                P.getExplanation(),
                P.getAddress(),
                vlogList // 위에서 만들어준 place_vlog_dto
        );
    }
}
