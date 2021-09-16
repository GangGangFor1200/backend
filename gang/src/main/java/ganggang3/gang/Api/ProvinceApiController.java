package ganggang3.gang.Api;


import ganggang3.gang.Service.ProvinceService;
import ganggang3.gang.domain.Province;
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


    @GetMapping("/api/province/findprovince")
    public Result findProvince(){
        List<Province> provinceList= provinceService.findAll();

        // Province Dto 만들기
        List<ProvinceDto> provinceDtoList = new ArrayList<>();
        provinceList.forEach(
                province -> {
                    List<CityDto> cityDtoList = province.getCityList().stream()
                            .map(s-> new CityDto(s.getName(),s.getCityLink()))
                            .collect(Collectors.toList());
                    provinceDtoList.add(new ProvinceDto(province.getName(),cityDtoList, province.getProvinceLink()));
                }
        );
        return new Result(provinceDtoList);
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class ProvinceDto{
        private String name;
        private List<CityDto> cityList;
        private String provinceLink;
    }
    @Data
    @AllArgsConstructor
    static class CityDto{
        private String name;
        private String cityLink;
    }
}
