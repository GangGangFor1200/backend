package ganggang3.gang.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import ganggang3.gang.domain_en.CourseEn;
import ganggang3.gang.domain_en.MyplaceEn;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String username;

    private String password;

    //private String access_token;


    @JsonManagedReference
    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL)
    private List<Course> courseList =new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL)
    private List<Myplace> myplaceList = new ArrayList<>();

    public static Member createMember(String username, String password){
        Member member=new Member();
        member.setUsername(username);
        member.setPassword(password);
        return member;
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL)
    private List<CourseEn> courseListEn =new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL)
    private List<MyplaceEn> myplaceListEn = new ArrayList<>();

}
