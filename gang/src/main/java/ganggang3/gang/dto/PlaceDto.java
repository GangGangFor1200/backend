package ganggang3.gang.dto;


import ganggang3.gang.domain.Place;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PlaceDto {
    private Long id;
    private String name;
    private double location_x;
    private double location_y;
    private String address;
    private String phone;
    private String placeUrl;
    private String image;
    private List<VlogDto> vlog_list;




    public static PlaceDto of (Place P, List<VlogDto> vlogList){
        return new PlaceDto(
                P.getId(),
                P.getName(),
                P.getLocation_x(),
                P.getLocation_y(),
                P.getPhone(),
                P.getAddress(),
                P.getPlaceUrl(),
                P.getImage(),
                vlogList // 위에서 만들어준 place_vlog_dto
        );
    }

}
