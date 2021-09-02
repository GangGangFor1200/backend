package com.example.ganggang2.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Place {

    @Id
    @GeneratedValue
    @Column(name = "place_id")
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_id")
    private Station station;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    private String location;

    private String explanation;

    private int rank;

    @OneToMany(mappedBy = "place",cascade = CascadeType.ALL)
    private List<Vlog> vlogList=new ArrayList<>();

    //order을 저장할 때, category와 station에도 추가되도록 setter만들기
    public void setCategory(Category category){
        this.category=category;
        category.getPlaceList().add(this);
    }
    public void setStation(Station station){
        this.station=station;
        station.getPlaceList().add(this);
    }
    public static Place createPlace(Category category,Station station){
        Place place=new Place();
        place.setCategory(category);
        place.setStation(station);
        return place;
    }

    public List<Vlog> getVlogList(int i){
        return vlogList.subList(i,i+3);
    }
}
