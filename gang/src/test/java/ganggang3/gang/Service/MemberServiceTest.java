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
        Long member_id=1234L;

        //when
        Member member=memberService.findById(member_id);

        //then
        assertEquals(member_id,member.getId(),0);

    }

    @Test
    @Transactional
    @Rollback(false)
    public void saveMember(){
        //given
        long member_id=1234;
        String access_token ="AwYsMqfvkQdn8YcwxBA9jP7-i46-l5lkOqapDAo9dJcAAAF8Y1pzdg";
        //when
        memberService.saveMember(member_id,access_token);
        //then
    }

}