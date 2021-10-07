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
    private Long id;

    private Long Kakaoid;
//    private String name;
//
//    private String password;



    @JsonManagedReference
    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL)
    private List<Course> courseList =new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL)
    private List<Myplace> myplaceList = new ArrayList<>();

    public static Member createMember(long kakaoid) {
        Member member=new Member();
        member.setKakaoid(kakaoid);
        return member;
    }
}
