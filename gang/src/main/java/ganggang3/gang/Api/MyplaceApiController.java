package ganggang3.gang.Api;

import ganggang3.gang.Service.MemberService;
import ganggang3.gang.Service.MyplaceService;
import ganggang3.gang.Service.PlaceService;
import ganggang3.gang.domain.Member;
import ganggang3.gang.domain.Myplace;
import ganggang3.gang.domain.MyplaceCourse;
import ganggang3.gang.domain.Place;

import ganggang3.gang.dto.MyplaceDto;
import ganggang3.gang.dto.PlaceDto;
import ganggang3.gang.dto.PlaceDtoVlog;
import ganggang3.gang.dto.VlogDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MyplaceApiController {

    private final MyplaceService myplaceService;
    private final MemberService memberService;
    private final PlaceService placeService;


    //security 넣기 전에는 미리 넣어 둔 member의 memberid로 myplace찾으면 됨
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
    @PostMapping("/api/myplace/add/{memberid}/{placeid}")
    public void addMyplace(@PathVariable("memberid") Long member_id,@PathVariable("placeid") Long place_id) {
        Member member=memberService.findById(member_id);
        Place place=placeService.findById(place_id);
        myplaceService.add(member,place);
    }
    @PostMapping("/api/myplace/addfromapi/{memberid}")
    public void addMyplaceFromapi(@PathVariable("memberid") Long member_id,@RequestBody Map<String,Object> map) {
        Member member=memberService.findById(member_id);
        myplaceService.addFromApi(member,map);

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
