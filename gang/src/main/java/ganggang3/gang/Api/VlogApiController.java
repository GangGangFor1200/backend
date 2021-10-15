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


@RestController
@RequiredArgsConstructor
public class VlogApiController {

    private final VlogService vlogService;
    private double x;
    private double y;

    @GetMapping("/api/vlog/findplace/{vlogurl}")
    public Result findPlace(@PathVariable("vlogurl") String vlogurl){
        x=0.0;
        y=0.0;
        List<Place> placeList=vlogService.findPlaceList(vlogurl);
        List<PlaceDtoVlog> placeDtoList=new ArrayList<>();
        //place Dto로  변환
        if (placeList!=null) {
            placeList.forEach(place -> {
                placeDtoList.add(PlaceDtoVlog.of(place));
                x+=place.getLocation_x();
                y+=place.getLocation_y();
                System.out.println(x);
            });
            x/=placeList.size();
            y/=placeList.size();

            System.out.println(placeList.size());
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
