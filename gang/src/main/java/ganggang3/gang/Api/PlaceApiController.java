package ganggang3.gang.Api;

import ganggang3.gang.Service.PlaceService;
import ganggang3.gang.domain.PlaceVlog;
import ganggang3.gang.domain.Vlog;
import ganggang3.gang.dto.VlogDto;
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

    @GetMapping("/api/place/findtop5/{city}/{category}")
    public Result findTop5(@PathVariable("city") long city_id, @PathVariable("category") long category_id){
        return new Result(placeService.getTop5(city_id, category_id));
    }

    //    FindTop5Response
    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }

}

