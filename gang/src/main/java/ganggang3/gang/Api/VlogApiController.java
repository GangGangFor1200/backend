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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
public class VlogApiController {

    private final VlogService vlogService;
    private double x=0.0;
    private double y=0.0;

    @GetMapping("/api/vlog/findplace/{vlogurl}")
    public Result findPlace(@PathVariable("vlogurl") String vlogurl){
        List<Place> placeList=vlogService.findPlaceList(vlogurl);
        List<PlaceDtoVlog> placeDtoList=new ArrayList<>();
        //place Dto로  변환
        if (placeList!=null) {
            placeList.forEach(place -> {
                placeDtoList.add(PlaceDtoVlog.of(place));
                x+=place.getLocation_x();
                y+=place.getLocation_y();
            });
            x/=placeList.size();
            y/=placeList.size();
        }
        return new Result(placeDtoList,x,y);
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
        private T x;
        private T y;
    }

}
