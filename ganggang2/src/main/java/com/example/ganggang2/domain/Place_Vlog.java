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

    public void setPlace(Place place){
        this.place=place;
        place.getPlace_vlogList().add(this);
    }

    public void setVlog(Vlog vlog){
        this.vlog=vlog;
        vlog.getPlace_vlogList().add(this);
    }

    public static Place_Vlog createPlace_Vlog(Place place, Vlog vlog){
        Place_Vlog place_vlog=new Place_Vlog();
        place_vlog.setPlace(place);
        place_vlog.setVlog(vlog);
        return place_vlog;
    }

    public Place_Vlog() {

    }
}
