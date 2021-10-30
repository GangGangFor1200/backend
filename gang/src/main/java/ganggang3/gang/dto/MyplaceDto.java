package ganggang3.gang.dto;

import ganggang3.gang.domain.Myplace;
import ganggang3.gang.domain_en.MyplaceEn;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MyplaceDto {

    private long id;
    private String name;
    private String category;
    private double location_x;
    private double location_y;
    private String address;
    private long placeId;

    public static MyplaceDto of (Myplace M){
        return new MyplaceDto(
                M.getId(),
                M.getName(),
                M.getCategory(),
                M.getLocation_x(),
                M.getLocation_y(),
                M.getAddress(),
                M.getPlaceId()
            );
    }
    public static MyplaceDto of (MyplaceEn M){
        return new MyplaceDto(
                M.getId(),
                M.getName(),
                M.getCategory(),
                M.getLocation_x(),
                M.getLocation_y(),
                M.getAddress(),
                M.getPlaceId()
        );
    }
}
