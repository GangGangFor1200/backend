//package ganggang3.gang.Controller;
//
//import ganggang3.gang.Service.CategoryService;
//import ganggang3.gang.Service.ProvinceService;
//import ganggang3.gang.domain.Category;
//import ganggang3.gang.domain.Province;
//import ganggang3.gang.domain.Station;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@Slf4j
//@RestController
//@RequiredArgsConstructor
//public class FirstController {
//
//
////    private final ProvinceService provinceService;
////    private final CategoryService categoryService;
////
////    @GetMapping("/main")
////    public Result firstpage(){
////        List<Province> provinceList= provinceService.findAll();
////        List<Category> categoryList =categoryService.findAll();
////
////        // Province Dto 만들기
////        List<ProvinceDto> provinceDtoList = new ArrayList<>();
////        for(int i=0;i<provinceList.size();i++){
////            Province province = provinceList.get(i);
////
////            List<StationDto> stationDtoList = province.getStationList().stream()
////                    .map(s-> new StationDto(s.getName()))
////                    .collect(Collectors.toList());
////            provinceDtoList.add(new ProvinceDto(province.getName(),stationDtoList));
////        }
////
////        //Categoey Dto 만들기
////        List<CategoryDto> categoryDtoList = categoryList.stream()
////                .map(c -> new CategoryDto(c.getName()))
////                .collect(Collectors.toList());
////
////        return new Result(provinceDtoList,categoryDtoList);
////    }
////
////    @Data
////    @AllArgsConstructor
////    static class Result<T>{
////        private T province;
////        private T category;
////    }
////
////    @Data
////    @AllArgsConstructor
////    static class CategoryDto{
////        private String name;
////
////    }
////    @Data
////    @AllArgsConstructor
////    static class ProvinceDto{
////        private String name;
////        private List<StationDto> stationList;
////    }
////    @Data
////    @AllArgsConstructor
////    static class StationDto{
////        private String name;
////    }
//
//
//}
