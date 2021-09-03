package com.example.ganggang2.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class Place_Vlog {

    @Id
    @GeneratedValue
    @Column(name = "place_vlog_id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vlog_id")
    private Vlog vlog;

    public Place_Vlog(Place place, Vlog vlog) {//vlogservice에서만
        this.place=place;
        this.vlog=vlog;
    }

    public Place_Vlog() {

    }
}
