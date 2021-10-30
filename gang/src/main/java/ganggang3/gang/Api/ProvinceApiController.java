package ganggang3.gang.Api;


import ganggang3.gang.Service.ProvinceService;
import ganggang3.gang.domain.Province;
import ganggang3.gang.domain.Station;
import ganggang3.gang.dto.CityDto;
import ganggang3.gang.dto.ProvinceDto;
import ganggang3.gang.dto.StationDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ProvinceApiController {
    private final ProvinceService provinceService;

    @GetMapping("/api/province/findall")
    public Result findAllProvince(){
        List<Province> provinceList= provinceService.findAll();

        // Province Dto 만들기
        List<ProvinceDto> provinceDtoList = new ArrayList<>();

        provinceList.forEach(
                province -> {
                    List<CityDto> cityDtoList = province.getCityList().stream()
                            .map(c-> new CityDto(c.getId(),c.getName(),c.getCityLink(),province.getId(),c.getStationList().stream()
                                    .map(s-> new StationDto(s.getName()))
                                    .collect(Collectors.toList())))
                            .collect(Collectors.toList());
                    provinceDtoList.add(new ProvinceDto(province.getName(), province.getProvinceLink(),cityDtoList));
                }
        );
        return new Result(provinceDtoList);
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }


}
