package com.example.ganggang2.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Vlog {

    @Id
    @GeneratedValue
    @Column(name = "vlog_id")
    private Long id;

    private String name;

    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    public void setPlace(Place place){
        this.place=place;
        place.getVlogList().add(this);
    }
    public static Vlog createVlog(Place place){
        Vlog vlog=new Vlog();
        vlog.setPlace(place);

        return vlog;
    }
}