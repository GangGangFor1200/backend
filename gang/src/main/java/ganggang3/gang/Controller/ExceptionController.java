package ganggang3.gang.Controller;

import ganggang3.gang.exception.NoDeliveryException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(NoDeliveryException.class)
    public ModelAndView noDelivery(NoDeliveryException nde) {
        ModelAndView mView=new ModelAndView();
        mView.addObject("exception",nde);
        mView.setViewName("error/data_access");

        return mView;
    }
}
