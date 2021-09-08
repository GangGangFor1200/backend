package ganggang3.gang.Controller;

import ganggang3.gang.Service.ProvinceService;
import ganggang3.gang.domain.Province;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/main")
@RequiredArgsConstructor
public class FirstController {


    private final ProvinceService provinceService;

    @GetMapping()
    public List<Province> firstpage(){
        return provinceService.findAll();
    }

}
