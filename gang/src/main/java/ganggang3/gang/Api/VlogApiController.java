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

    @GetMapping("/api/vlog/findplace/{vlogid}")
    public Result findPlace(@PathVariable("vlogid") long vlogid){
        List<Place> placeList=vlogService.findPlaceList(vlogid);
        List<PlaceDtoVlog> placeDtoList=new ArrayList<>();
        //place Dto로  변환
        if (placeList!=null) {
            placeDtoList = placeList.stream()
                    .map(p -> PlaceDtoVlog.of(p))
                    .collect(Collectors.toList());
        }
            return new Result(placeDtoList);

    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }


}
