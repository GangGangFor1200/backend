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
public class Course {

    @Id
    @GeneratedValue
    @Column(name = "Course_id")
    private Long id;

    private String name;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @JsonManagedReference
    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    private List<MyplaceCourse> myplace_courseList=new ArrayList<>();


    public static Course createCourse(String name, Member member){
        Course course = new Course();
        course.setName(name);
        course.setMember(member);
        return course;
    }

}
