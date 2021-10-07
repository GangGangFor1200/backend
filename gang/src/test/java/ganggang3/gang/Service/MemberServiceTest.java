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
        long memberId=2;

        //when
        Member member=memberService.findById(memberId);

        //then
        assertEquals(memberId,member.getId(),0);

    }

    @Test
    @Transactional
    @Rollback(false)
    public void saveMember(){
        //given
        Long kakaoId=1940703666L;
        //when
        memberService.saveMember(kakaoId);
        //then
    }

}