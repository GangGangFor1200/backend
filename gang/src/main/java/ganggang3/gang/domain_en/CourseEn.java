package ganggang3.gang.domain_en;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import ganggang3.gang.domain.MemberEn;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class CourseEn {

    @Id
    @GeneratedValue
    @Column(name = "course_en_id")
    private Long id;

    private String name;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEn member;

    @JsonManagedReference
    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    private List<MyplaceCourseEn> myplace_courseList=new ArrayList<>();


    public static CourseEn createCourse(String name, MemberEn member){
        CourseEn courseEn = new CourseEn();
        courseEn.setName(name);
        courseEn.setMember(member);
        return courseEn;
    }

}
