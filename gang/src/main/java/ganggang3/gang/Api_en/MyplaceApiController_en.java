package ganggang3.gang.Api_en;

import ganggang3.gang.Api.MyplaceApiController;
import ganggang3.gang.Service.MemberService;
import ganggang3.gang.Service.MyplaceService;
import ganggang3.gang.Service.PlaceService;
import ganggang3.gang.Service_en.MyplaceService_en;
import ganggang3.gang.Service_en.PlaceService_en;
import ganggang3.gang.domain.Member;
import ganggang3.gang.domain.Myplace;
import ganggang3.gang.domain.Place;

import ganggang3.gang.domain_en.MyplaceEn;
import ganggang3.gang.domain_en.PlaceEn;
import ganggang3.gang.dto.MyplaceDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MyplaceApiController_en {

    private final MyplaceService_en myplaceService;
    private final MemberService memberService;
    private final PlaceService_en placeService;

    @PostMapping("/api/en/myplace/isexists/{memberid}")
    public List<Long> isExists(@PathVariable("memberid") Long member_id, @RequestBody Map<String,Long>map){
        Member member=memberService.findById(member_id);
        List<Long> exists = myplaceService.isExists(member, map);
        return exists;
    }
    @PostMapping("/api/en/myplace/addfromstart/{memberid}")
    public Result addMyplaceFromstart(@PathVariable("memberid") Long member_id, @RequestBody Map<String,Object> map) {
        Member member=memberService.findById(member_id);
        MyplaceDto myplaceDto = new MyplaceDto(
                Long.valueOf(map.get("id").toString()),
                map.get("name").toString(),
                map.get("categoey").toString(),
                Double.valueOf(map.get("location_x").toString()),
                Double.valueOf(map.get("location_y").toString()),
                map.get("address").toString(),
                Long.valueOf(map.get("placeId").toString())
        );
        return new Result(myplaceDto);
    }
    @GetMapping("/api/en/myplace/findall/{memberid}")
    public Result findAllMyplace(@PathVariable("memberid") Long member_id){
        Member member=memberService.findById(member_id);
        List<MyplaceEn> myplaceList = myplaceService.findMyplaceList(member);

        List<MyplaceDto> myplaceDtoList = new ArrayList<>();

        if (myplaceList!=null) {
            myplaceList.forEach(p -> {
                MyplaceDto md = new MyplaceDto(
                        p.getId(),
                        p.getName(),
                        p.getCategory(),
                        p.getLocation_x(),
                        p.getLocation_y(),
                        p.getAddress(),
                        p.getPlaceId()
                );
                myplaceDtoList.add(md);
            });
        }
        return new Result(myplaceDtoList);

    }
    @PostMapping("/api/en/myplace/add/{memberid}/{placeid}")
    public void addMyplace(@PathVariable("memberid") Long member_id,@PathVariable("placeid") Long place_id) {
        Member member=memberService.findById(member_id);
        PlaceEn place=placeService.findById(place_id);
        myplaceService.add(member,place);
    }
    @PostMapping("/api/en/myplace/addfromapi/{memberid}")
    public Map<String,Long> addMyplaceFromapi(@PathVariable("memberid") Long member_id,@RequestBody Map<String,Object> map) {
        Member member=memberService.findById(member_id);
        MyplaceEn myplaceEn=myplaceService.addFromApi(member,map);
        Map<String,Long> map1=new HashMap<>();
        map1.put("myplaceid",myplaceEn.getId());
        return map1;
    }
    @DeleteMapping("/api/en/myplace/deletebyplace/{memberid}/{placeid}")
    public void deleteMyplaceByPlace(@PathVariable("memberid") Long member_id,@PathVariable("placeid") Long place_id){
        Member member=memberService.findById(member_id);
        PlaceEn place=placeService.findById(place_id);
        myplaceService.deleteByPlace(member,place);
    }
    @DeleteMapping("/api/en/myplace/deletebymyplace/{memberid}/{myplacename}")
    public void deleteMyplaceByMyplace(@PathVariable("memberid") Long member_id,@PathVariable("myplacename") String myplace_name){
        Member member=memberService.findById(member_id);
        MyplaceEn myplace=myplaceService.findByName(myplace_name);
        myplaceService.deleteByMyplace(member,myplace);
    }


    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }
}
