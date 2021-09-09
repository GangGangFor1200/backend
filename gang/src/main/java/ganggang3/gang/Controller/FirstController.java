package ganggang3.gang.Controller;

import ganggang3.gang.Service.CategoryService;
import ganggang3.gang.Service.ProvinceService;
import ganggang3.gang.domain.Province;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/main")
@RequiredArgsConstructor
public class FirstController {


    private final ProvinceService provinceService;
    private final CategoryService categoryService;

    @GetMapping()
    public Map<String, Object> firstpage(){
        Map<String,Object> map=new HashMap<>();
        map.put("category",categoryService.findAll());
        map.put("province",provinceService.findAll());
        return map;
    }

}
