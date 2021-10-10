package ganggang3.gang.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @Column(name = "member_id")
    private String id;

    private String refresh_token;


    @JsonManagedReference
    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL)
    private List<Course> courseList =new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL)
    private List<Myplace> myplaceList = new ArrayList<>();

    public static Member createMember(String member_id,String refresh_token) {
        Member member=new Member();
        member.setId(member_id);
        member.setRefresh_token(refresh_token);
        return member;
    }
}
