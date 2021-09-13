package ganggang3.gang.Api;

import ganggang3.gang.Service.PlaceService;
import ganggang3.gang.domain.Place;
import ganggang3.gang.domain.Place_Vlog;
import ganggang3.gang.domain.Vlog;
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
    public Result findTop5(@PathVariable("city") int city_id, @PathVariable("category") int category_id){

        List<Place> findTop5 = placeService.findTOP5(city_id,category_id);

        List<PlaceDto> collect=new ArrayList<>();
        for(int i=0;i< findTop5.size();i++){
            Place P = findTop5.get(i);
            List<Place_Vlog> p_v_list = P.getPlace_vlogList();
            //Top5 place 의 vlog_list
            List<Vlog_Dto> vlog_list=new ArrayList<>();
            for(int j = 0; j< p_v_list.size(); j++){
                Place_Vlog pv = p_v_list.get(j);
                Vlog v = pv.getVlog();
                //Top5 place의 Vlog DTO를 할당
                vlog_list.add(
                        new Vlog_Dto(
                        v.getName(),
                        v.getUrl()
                ));
            }

            //Place Dto 할당
            PlaceDto pd = new PlaceDto(
                    P.getName(),
                    P.getLocation_x(),
                    P.getLocation_y(),
                    P.getExplanation(),
                    P.getAddress(),
                    vlog_list // 위에서 만들어준 place_vlog_dto
            );
            //리스트에 할당
            collect.add(pd);
        }
        return new Result(collect);
    }
//    FindTop5Response
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
        private List<Vlog_Dto> vlog_list;
    }


    @Data
    @AllArgsConstructor
    static class Vlog_Dto{
        private String name;
        private String url;
    }
}
