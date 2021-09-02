package com.example.ganggang2.domain;

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

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    private List<Favorite_Course> favoriteCourseList=new ArrayList<>();
}
