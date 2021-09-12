package ganggang3.gang.api;

import ganggang3.gang.Service.VlogService;
import ganggang3.gang.domain.Place;
import ganggang3.gang.domain.Vlog;
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
        List<PlaceDto> placeDtoList = placeList.stream()
                .map(p -> new PlaceDto(
                        p.getName(),
                        p.getLocation_x(),
                        p.getLocation_y(),
                        p.getExplanation(),
                        p.getAddress()
                        )
                )
                .collect(Collectors.toList());
        return new Result(placeDtoList);
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }
    @Data
    @AllArgsConstructor
    static class PlaceDto{
        private String name;
        private double location_x;
        private double location_y;
        private String explanation;
        private String address;
    }
}
