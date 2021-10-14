package ganggang3.gang.domain_en;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import ganggang3.gang.domain.Member;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)

//밖에서 생성자 못쓰게 하는거 createOrderitem만 쓰도록하는거 롬복
public class MyplaceEn {
    @Id
    @GeneratedValue
    @Column(name = "myplace_en_id")
    private Long id;

    private String name;

    private String category;

    private double location_x;
    private double location_y;

    private String address;

    @JsonManagedReference
    @OneToMany(mappedBy = "myplace",cascade = CascadeType.ALL)
    private List<MyplaceCourseEn> myplace_courseList=new ArrayList<>();

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public static MyplaceEn createMyplace(String name, String category, double location_x,
                                          double location_y, String address, Member member) {

        MyplaceEn myplace = new MyplaceEn();
//        myplace.setId(Long.valueOf(id));
        myplace.setName(name);
        myplace.setCategory(category);
        myplace.setLocation_x(location_x);
        myplace.setLocation_y(location_y);
        myplace.setAddress(address);
        myplace.setMember(member);

        return myplace;

    }



}
