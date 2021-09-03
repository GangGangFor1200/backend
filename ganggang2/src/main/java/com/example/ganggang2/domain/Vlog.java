package com.example.ganggang2.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Vlog {

    @Id
    @GeneratedValue
    @Column(name = "vlog_id")
    private Long id;

    private String url;


    @OneToMany(mappedBy = "place",cascade = CascadeType.ALL)
    private List<Place_Vlog> place_vlogList=new ArrayList<>();


}