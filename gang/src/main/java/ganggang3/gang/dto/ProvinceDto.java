package ganggang3.gang.dto;

import ganggang3.gang.Api.ProvinceApiController;
import ganggang3.gang.domain.Place;
import ganggang3.gang.domain.Province;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProvinceDto {

    private String name;
    private String provinceLink;
    private List<CityDto> cityList;

    public static ProvinceDto of (Province P, List<CityDto> cityDtoList){
        return new ProvinceDto(
                P.getName(),
                P.getProvinceLink(),
                cityDtoList
        );
    }
}
