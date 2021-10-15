package ganggang3.gang.dto;

import ganggang3.gang.domain.MyplaceEn;
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


    public static MyplaceDto of (MyplaceEn M){
        return new MyplaceDto(
                M.getId(),
                M.getName(),
                M.getCategory(),
                M.getLocation_x(),
                M.getLocation_y(),
                M.getAddress()
            );
    }
}
