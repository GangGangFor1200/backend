package ganggang3.gang.Controller;

import ganggang3.gang.exception.DatabaseException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@ControllerAdvice
public class ExceptionController {

    //json 어떤 형식으로 보내줄지 프론트애들한테 물어보기
    //지금은 {"message":"db문제 발생"}

    @ExceptionHandler(DatabaseException.class)
    @ResponseBody
    public Error DataException(DatabaseException nde) {
        Error error=new Error(nde.getMessage());
        return error;
    }
    @Data
    @AllArgsConstructor
    static class Error{
        String Message;
    }
}
