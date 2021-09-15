package ganggang3.gang.dto;

import ganggang3.gang.domain.Place;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PlaceDtoVlog {
    private String name;
    private double location_x;
    private double location_y;
    private String explanation;
    private String address;

    public static PlaceDtoVlog of (Place P){
        return new PlaceDtoVlog(
                P.getName(),
                P.getLocation_x(),
                P.getLocation_y(),
                P.getExplanation(),
                P.getAddress()
        );
    }
}
