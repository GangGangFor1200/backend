package ganggang3.gang.Service;

import ganggang3.gang.domain.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired
    MemberService memberService;

    @Test
    public void findMember(){
        //given
        String access="jPDl5c7aAbAlVmxCz-CYlJ8qpj6OorCgZZPA4AopcJ8AAAF8Y3LQQQ";

        //when
        Member member=memberService.findById(access);

        //then
        assertEquals(access,member.getId());

    }

    @Test
    @Transactional
    @Rollback(false)
    public void saveMember(){
        //given
        String access_token ="AwYsMqfvkQdn8YcwxBA9jP7-i46-l5lkOqapDAo9dJcAAAF8Y1pzdg";
        String refresh_token="1234";
        //when
        memberService.saveMember(access_token,refresh_token);
        //then
    }

}