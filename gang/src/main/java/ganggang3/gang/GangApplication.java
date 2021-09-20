package ganggang3.gang;

import ganggang3.gang.Service.MemberService;
import ganggang3.gang.domain.Member;
import ganggang3.gang.dto.MyplaceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class GangApplication {

    public static void main(String[] args) {


        SpringApplication.run(GangApplication.class, args);


    }

}
