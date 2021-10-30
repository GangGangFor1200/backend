package ganggang3.gang.Api;

import ganggang3.gang.Service.MemberService;
import ganggang3.gang.Service.MyplaceService;
import ganggang3.gang.Service.PlaceService;
import ganggang3.gang.domain.Member;
import ganggang3.gang.domain.Myplace;
import ganggang3.gang.domain.Place;

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
public class MyplaceApiController {

    private final MyplaceService myplaceService;
    private final MemberService memberService;
    private final PlaceService placeService;


    @GetMapping("/api/myplace/findall/{memberid}")
    public Result findAllMyplace(@PathVariable("memberid") Long member_id){
        Member member=memberService.findById(member_id);
        List<Myplace> myplaceList = myplaceService.findMyplaceList(member);
        List<MyplaceDto> myplaceDtoList = new ArrayList<>();

        if (myplaceList!=null) {
            myplaceList.forEach(p -> {
                MyplaceDto md = MyplaceDto.of(p);
                myplaceDtoList.add(md);
            });
        }

        return new Result(myplaceDtoList);

    }
    @PostMapping("/api/myplace/isexists/{memberid}")
    public List<Long> isExists(@PathVariable("memberid") Long member_id, @RequestBody Map<String,Long>map){
        Member member=memberService.findById(member_id);
        List<Long> exists = myplaceService.isExists(member, map);
        return exists;
    }

    @PostMapping("/api/myplace/add/{memberid}/{placeid}")
    public void addMyplace(@PathVariable("memberid") Long member_id,@PathVariable("placeid") Long place_id) {
        Member member=memberService.findById(member_id);
        Place place=placeService.findById(place_id);
        myplaceService.add(member,place);
    }
    @PostMapping("/api/myplace/addfromapi/{memberid}")
    public Map<String,Long> addMyplaceFromapi(@PathVariable("memberid") Long member_id,@RequestBody Map<String,Object> map) {
        Member member=memberService.findById(member_id);
        Myplace myplace=myplaceService.addFromApi(member,map);
        Map<String,Long> map1=new HashMap<>();
        map1.put("myplaceid",myplace.getId());
        return map1;
    }
    @PostMapping("/api/myplace/addfromstart/{memberid}")
    public Result addMyplaceFromstart(@PathVariable("memberid") Long member_id,@RequestBody Map<String,Object> map) {
        Member member=memberService.findById(member_id);
        MyplaceDto myplaceDto = MyplaceDto.of(myplaceService.addFromApi(member, map));
        return new Result(myplaceDto);
    }
    @DeleteMapping("/api/myplace/deletebyplace/{memberid}/{placeid}")
    public void deleteMyplaceByPlace(@PathVariable("memberid") Long member_id,@PathVariable("placeid") Long place_id){
        Member member=memberService.findById(member_id);
        Place place=placeService.findById(place_id);
        myplaceService.deleteByPlace(member,place);
    }
    @DeleteMapping("/api/myplace/deletebymyplace/{memberid}/{myplaceid}")
    public void deleteMyplaceByMyplace(@PathVariable("memberid") Long member_id,@PathVariable("myplaceid") Long myplace_id){
        Member member=memberService.findById(member_id);
        Myplace myplace=myplaceService.findById(myplace_id);
        myplaceService.deleteByMyplace(member,myplace);
    }


    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }
}
