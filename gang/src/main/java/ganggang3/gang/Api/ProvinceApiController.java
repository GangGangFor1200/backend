package ganggang3.gang.Api;

import ganggang3.gang.Controller.FirstController;
import ganggang3.gang.Service.CategoryService;
import ganggang3.gang.Service.ProvinceService;
import ganggang3.gang.domain.Category;
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
        for(int i=0;i<provinceList.size();i++){
            Province province = provinceList.get(i);

            List<StationDto> stationDtoList = province.getStationList().stream()
                    .map(s-> new StationDto(s.getName()))
                    .collect(Collectors.toList());
            provinceDtoList.add(new ProvinceDto(province.getName(),stationDtoList));
        }

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
        private List<StationDto> stationList;
    }
    @Data
    @AllArgsConstructor
    static class StationDto{
        private String name;
    }
}
