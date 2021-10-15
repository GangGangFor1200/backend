package ganggang3.gang.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Myplace {
    @Id
    @GeneratedValue
    @Column(name = "myplace_id")
    private Long id;

    private String name;

    private String category;

    private double location_x;
    private double location_y;

    private String address;

    @JsonManagedReference
    @OneToMany(mappedBy = "myplace",cascade = CascadeType.ALL)
    private List<MyplaceCourse> myplace_courseList=new ArrayList<>();

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public static Myplace createMyplace(String name, String category, double location_x,
                                        double location_y, String address, Member member) {

        Myplace myplace = new Myplace();
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
