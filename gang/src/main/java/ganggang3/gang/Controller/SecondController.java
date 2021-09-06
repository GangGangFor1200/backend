package ganggang3.gang.Controller;

import ganggang3.gang.Service.PlaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping
@RequiredArgsConstructor
public class SecondController {

    private final PlaceService placeService;

    @GetMapping("/{station}/{category}")
    public String TOP5(@PathVariable("station") int station_id, @PathVariable("category") int category_id, Model model){

        return "";
    }
    //myplace 정보 가져와야함
    @PostMapping("/{station}/{category}")
    public String exmaple(@PathVariable("station") int station_id, @PathVariable("category") int category_id, Model model){
        return "";
    }

}
