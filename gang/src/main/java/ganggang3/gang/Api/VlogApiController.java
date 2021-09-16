package ganggang3.gang.Api;

import ganggang3.gang.Service.VlogService;
import ganggang3.gang.domain.Place;
import ganggang3.gang.dto.PlaceDtoVlog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class VlogApiController {

    private final VlogService vlogService;

    @GetMapping("api/vlog/findplace/{vlog}")
    public Result findPlace(@PathVariable("vlog") int vlogId){
        List<Place> placeList=vlogService.findPlaceList(vlogId);
        //place Dto로  변환
        List<PlaceDtoVlog> placeDtoList = placeList.stream()
                .map(p -> PlaceDtoVlog.of(p))
                .collect(Collectors.toList());
        return new Result(placeDtoList);
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }

    static class PlaceDto{
        private String name;
        private double location_x;
        private double location_y;
        private String explanation;
        private String address;
    }

}
