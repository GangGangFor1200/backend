package ganggang3.gang.Api;

import ganggang3.gang.Service.MemberService;
import ganggang3.gang.Service.MyplaceService;
import ganggang3.gang.domain.Member;
import ganggang3.gang.dto.MyplaceDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MyplaceApiController {
    @Autowired
    MyplaceService myplaceService;
    MemberService memberService;

    @Test
    public void findMember(){
        //given
        long memberId=1;

        //when
        Member member=memberService.findById(memberId);

        //then
        assertEquals(memberId,member.getId(),0);
    }

    @Test
    public void findAllMyplace(){
        //given
        long memberId=1;
        Member member=memberService.findById(memberId);

        //when
        List<MyplaceDto> myplaceDtoList=myplaceService.ApifindMyplaceList(member);

        //then
        assertEquals(1,myplaceDtoList.size(),0);
    }

    @Test
    public void addMyplace(){
        //given

        //when

        //then
    }
    @Test
    public void deleteMyplace(){
        //given

        //when

        //then
    }

}
