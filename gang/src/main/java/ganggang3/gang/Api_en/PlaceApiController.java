package ganggang3.gang.Api_en;

import ganggang3.gang.Service.PlaceService;
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
public class PlaceApiController {

    private final PlaceService placeService;

    @GetMapping("/api/place/findtop5/{cityid}/{categoryid}")
    public Result findTop5(@PathVariable("cityid") long city_id, @PathVariable("categoryid") long category_id){
        return new Result(placeService.getTop5(city_id, category_id));
    }

    //    FindTop5Response
    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }

}

