package ganggang3.gang.Api;

import ganggang3.gang.Service.StationService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StationApiController {
    private final StationService stationService;

    @GetMapping("/api/station/findall")
    public Result findAll(){
        return new Result(stationService.findAll());
    }

    @GetMapping("/api/station/{cityid}")
    public Result findbycity(@PathVariable("cityid") Long city_id){
        return  new Result(stationService.findAllByCity(city_id));
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }
}