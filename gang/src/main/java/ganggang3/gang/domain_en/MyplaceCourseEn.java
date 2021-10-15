package ganggang3.gang.domain_en;

import com.fasterxml.jackson.annotation.JsonBackReference;
import ganggang3.gang.domain.CourseEn;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class MyplaceCourseEn {
    @Id
    @GeneratedValue
    @Column(name = "myplace_course_en_id")
    private Long id;


    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "myplace_en_id")
    private MyplaceEn myplace;


    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_en_id")
    private CourseEn course;

    public void setMyplace(MyplaceEn myplace){
        this.myplace=myplace;
        myplace.getMyplace_courseList().add(this);
    }

    public void setCourse(CourseEn course){
        this.course=course;
        course.getMyplace_courseList().add(this);
    }
    public static MyplaceCourseEn createMyplaceCourse(MyplaceEn myplace, CourseEn course){
        MyplaceCourseEn myplaceCourse = new MyplaceCourseEn();
        myplaceCourse.setCourse(course);
        myplaceCourse.setMyplace(myplace);
        return myplaceCourse;

    }

}

