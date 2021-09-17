package ganggang3.gang.Api;

import ganggang3.gang.Service.MyplaceService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MyplaceApiController {

    private final MyplaceService myplaceService;


    @GetMapping("/api/myplace/findallmyplace/{memberid}")
    public void findAllMyplace(){

    }
    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }
}
