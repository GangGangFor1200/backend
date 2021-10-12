package ganggang3.gang.Api;

import com.fasterxml.jackson.databind.ObjectMapper;
import ganggang3.gang.Repository.ProvinceRepository;
import ganggang3.gang.Service.CategoryService;
import ganggang3.gang.Service.CourseService;
import ganggang3.gang.Service.MemberService;
import ganggang3.gang.Service.MyplaceService;
import ganggang3.gang.domain.Course;
import ganggang3.gang.domain.Member;
import ganggang3.gang.domain.Myplace;
import ganggang3.gang.dto.MyplaceDto;
import ganggang3.gang.dto.PlaceDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PageApiTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ProvinceRepository provinceRepository;

    @Autowired
    CategoryService categoryService;

    @Autowired
    MemberService memberService;

    @Autowired
    MyplaceService myplaceService;

    @Autowired
    CourseService courseService;

    @Test
    public void firstpageTest() throws Exception{

        mockMvc.perform(get("/api/category/findcategory"))
                .andExpect(jsonPath("$.data[0].name").value("관광명소"))
                .andExpect(jsonPath("$.data.length()").value(3))
                .andExpect(status().isOk())
                .andDo(print());
        mockMvc.perform(get("/api/province/findprovince"))
                .andExpect(jsonPath("$.data[0].name").value("경기도"))
                .andExpect(jsonPath("$.data.length()").value(14))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void secondpageTest() throws Exception{
        mockMvc.perform(get("/api/place/findtop5/{city}/{category}","1","1"))
                .andExpect(jsonPath("$.data[0].name").value("순천만습지1"))
                .andExpect(jsonPath("$.data.length()").value(5))
                .andExpect(jsonPath("$.data[0].vlog_list[0].id").value(1))
                .andExpect(jsonPath("$.data[0].vlog_list.length()").value(7))
                .andExpect(status().isOk())
                .andDo(print());

        mockMvc.perform(get("/api/vlog/findplace/{vlog}",1))
                .andExpect(jsonPath("$.data[0].name").value("순천만습지1"))
                .andExpect(jsonPath("$.data.length()").value(10))
                .andExpect(status().isOk())
                .andDo(print());

    }
//1. addMyplace
//2. deleteMyplaceByPlace
    @Test
    @Transactional
    @Rollback(false)
    public void mypage를제외한myplaceTest() throws Exception{
        long addplaceid=1;
        mockMvc.perform(post("/api/myplace/addmyplace/1/{placeid}",addplaceid))
                .andExpect(status().isOk())
                .andDo(print());
        addplaceid=2;
        mockMvc.perform(post("/api/myplace/addmyplace/1/{placeid}",addplaceid))
                .andExpect(status().isOk())
                .andDo(print());
        //잘들어갔는지확인~~
        mockMvc.perform(get("/api/myplace/findallmyplace/{memberid}",1))
                .andExpect(jsonPath("$.data.length()").value(2))
                .andExpect(jsonPath("$.data[0].name").value("순천만습지1"))
                .andExpect(jsonPath("$.data[1].name").value("순천만습지2"))
                .andExpect(status().isOk())
                .andDo(print());
        long deleteplaceid=1;
        mockMvc.perform(delete("/api/myplace/deletemyplacebyplace/{memberid}/{placeid}",1,deleteplaceid))
                .andExpect(status().isOk())
                .andDo(print());
        mockMvc.perform(get("/api/myplace/findallmyplace/{memberid}",1))
                .andExpect(jsonPath("$.data.length()").value(1))
                .andExpect(jsonPath("$.data[0].name").value("순천만습지2"))
                .andExpect(status().isOk())
                .andDo(print());
        //없는거 삭제해보기~~
        //error남 -> @Rollback(false)하고 돌리면 transaction하기 전에 오류나서 롤백 어쩌구함
        mockMvc.perform(delete("/api/myplace/deletemyplacebyplace/{memberid}/{placeid}",1,deleteplaceid))
                .andExpect(status().isOk())
                .andDo(print());

    }


    @Test
    //@Transactional
    //@Rollback(false)
    public void mypageTest() throws Exception{

//        //1 -> findallmyplace
//        mockMvc.perform(get("/api/myplace/findallmyplace/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data[0].name").value("순천만습지3"))
//                .andExpect(jsonPath("$.data.length()").value(8))
//                .andDo(print());
//
//        //2 -> deletemyplacebymyplace
//        mockMvc.perform(delete("/api/myplace/deletemyplacebymyplace/1/3"))
//                .andExpect(status().isOk())
//                .andDo(print());
//
//        mockMvc.perform(get("/api/myplace/findallmyplace/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data[0].name").value("순천만습지4"))
//                .andExpect(jsonPath("$.data.length()").value(7))
//                .andDo(print());

        //3 -> course add
        Long member_id=1234L;
        Member member=memberService.findById(member_id);
        //일단 test는 member가 가진 myplaceList로
        List<Myplace> myplaceList = myplaceService.findMyplaceList(member);
        //실제 프론트에서는 myplaceDto가 넘어올거임
        List<MyplaceDto> myplaceDtoList = new ArrayList<>();
        myplaceList.forEach(myplace -> {
            myplaceDtoList.add(MyplaceDto.of(myplace));
        });
        Map<String,Object> addmap=new HashMap<>();
        String course_name = "course_name";
        addmap.put("name",course_name);
        addmap.put("myplaceList",myplaceDtoList);

        String content = objectMapper.writeValueAsString(addmap);
        mockMvc.perform(post("/api/course/add/{member_id}",member_id)
                .content(content)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        //5 -> course findAll
        mockMvc.perform(get("/api/course/findall/{member}",member_id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[-1].name").value(course_name))
                .andDo(print());

        //4 -> course update
        Optional<Course> course = courseService.findByNameAndMember(course_name, member);
        Long id = course.get().getId();
        myplaceList = myplaceService.findMyplaceList(member);
        List<MyplaceDto> updateMyplaceDtoList = new ArrayList<>();
        myplaceList.forEach(myplace -> {
            updateMyplaceDtoList.add(MyplaceDto.of(myplace));
        });
        //when


        //list줄이기
        List<MyplaceDto> newDtoList=new ArrayList<>(updateMyplaceDtoList.subList(0,2));
        int size= newDtoList.size();
        Map<String,Object> updatemap=new HashMap<>();
        String changeedName = "course_newname";
        updatemap.put("name",changeedName);
        updatemap.put("myplaceList",newDtoList);
        //then
        content = objectMapper.writeValueAsString(updatemap);
        System.out.println("jurl : "+content);
        mockMvc.perform(put("/api/course/update/{member}/{course}",member_id,id)
                .content(content)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        mockMvc.perform(get("/api/course/findall/{member}",member_id))
                .andExpect(status().isOk())
                //바뀐 이름 체크
                .andExpect(jsonPath("$.data[-1].name").value(changeedName))
                .andDo(print());
        //6
        mockMvc.perform(get("/api/myplacecourse/findall/{course}",id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.length()").value(size))
                .andExpect(jsonPath("$.data[0].myplaceDto.name").value(newDtoList.get(0).getName()))
                .andDo(print());


        //myplaceList update됐는지 확인
        // 다른 리스트로 교체
        newDtoList=new ArrayList<>(updateMyplaceDtoList.subList(4,7));
        size= newDtoList.size();
        Map<String,Object> newupdatemap=new HashMap<>();
        newupdatemap.put("name",changeedName);
        newupdatemap.put("myplaceList",newDtoList);
        //then
        content = objectMapper.writeValueAsString(newupdatemap);
        System.out.println(content);
        mockMvc.perform(put("/api/course/update/{member}/{course}",member_id,id)
                .content(content)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        //myplaceList update됐는지 확인
        mockMvc.perform(get("/api/myplacecourse/findall/{course}",id))
                .andExpect(status().isOk())
                //바뀐 이름 체크
                .andExpect(jsonPath("$.data.length()").value(size))
                .andExpect(jsonPath("$.data[0].myplaceDto.name").value(newDtoList.get(0).getName()))
                .andDo(print());
    }
}
