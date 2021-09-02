package com.example.ganggang2.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Favorite {

    @Id
    @GeneratedValue
    @Column(name = "favorite_id")
    private Long id;

    private String name;

    private String category;

    private String explanation;

    private double locationx;
    private double locationy;

    @OneToMany(mappedBy = "favorite",cascade = CascadeType.ALL)
    private List<Favorite_Course> favoriteCourseList=new ArrayList<>();
}
