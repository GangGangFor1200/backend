package ganggang3.gang.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class MyplaceCourse {
    @Id
    @GeneratedValue
    @Column(name = "myplace_course_id")
    private Long id;


    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "myplace_id")
    private Myplace myplace;


    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private CourseEn course;

    public void setMyplace(Myplace myplace){
        this.myplace=myplace;
        myplace.getMyplace_courseList().add(this);
    }

    public void setCourse(CourseEn course){
        this.course=course;
        course.getMyplace_courseList().add(this);
    }
    public static MyplaceCourse createMyplaceCourse(Myplace myplace, CourseEn course){
        MyplaceCourse myplaceCourse = new MyplaceCourse();
        myplaceCourse.setCourse(course);
        myplaceCourse.setMyplace(myplace);
        return myplaceCourse;

    }

}

