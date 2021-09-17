package ganggang3.gang.dto;

import ganggang3.gang.domain.Station;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StationDto {

    private String name;

    public static StationDto of(Station S){
        return new StationDto(
                S.getName()
        );
    }
}
