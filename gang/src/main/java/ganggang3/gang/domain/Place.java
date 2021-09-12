package ganggang3.gang.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_id")
    private Station station;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    private double location_x;
    private double location_y;

    private String explanation;

    private String address;

    private Long count;

    @OneToMany(mappedBy = "place",cascade = CascadeType.ALL)
    private List<Place_Vlog> place_vlogList=new ArrayList<>();

    public void Countplus(){
        this.count++;
    }

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

}
