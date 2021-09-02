package com.example.ganggang2.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Station {

    @Id
    @GeneratedValue
    @Column(name="statuon_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "station",cascade = CascadeType.ALL)
    private List<Place> placeList=new ArrayList<>();

}