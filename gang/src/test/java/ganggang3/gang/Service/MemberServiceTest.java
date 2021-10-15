package ganggang3.gang.Service;

import ganggang3.gang.domain.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

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
        String member_name="jurl";

        //when
        Member member=memberService.findByUsername(member_name);

        //then
        assertEquals(member_name,member.getUsername());

    }

    @Test
    @Transactional
    @Rollback(false)
    public void saveMember(){
        //given
        String username="jurl";
        String password="1234";
        //when
        memberService.saveMember(username,password);
        //then
    }

}