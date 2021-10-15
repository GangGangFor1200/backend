package ganggang3.gang.Api_en;

import ganggang3.gang.Service.CategoryService;
import ganggang3.gang.Service.MemberService;
import ganggang3.gang.domain.CategoryEn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequiredArgsConstructor
public class CategoryApiController {

    private final CategoryService categoryService;
    private final MemberService memberService;

    @GetMapping("/api/category/findall")
    public Result findAllCategory() {


        List<CategoryEn> categoryList =categoryService.findAll();

        //Categoey Dto 만들기
        List<CategoryDto> categoryDtoList = categoryList.stream()
                .map(c -> new CategoryDto(c.getId(),c.getName()))
                .collect(Collectors.toList());

        return new Result(categoryDtoList);
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class CategoryDto {
        private long id;
        private String name;

    }


}
