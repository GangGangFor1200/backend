package ganggang3.gang.dto;

import ganggang3.gang.Api.ProvinceApiController;
import ganggang3.gang.domain.City;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CityDto {

    private String name;
    private String cityLink;
    private long provinceid;
    private List<StationDto> stationList;

    public static CityDto of (City C,List<StationDto> stationDtoList){
        return new CityDto(
                C.getName(),
                C.getCityLink(),
                C.getProvince().getId(),
                stationDtoList
        );
    }
}
