package ganggang3.gang.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @Column(name = "Course_id")
    private Long id;

    private String name;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEn member;

    @JsonManagedReference
    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    private List<MyplaceCourse> myplace_courseList=new ArrayList<>();


    public static CourseEn createCourse(String name, MemberEn member){
        CourseEn course = new CourseEn();
        course.setName(name);
        course.setMember(member);
        return course;
    }

}
