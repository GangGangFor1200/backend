package ganggang3.gang.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class City {

    @Id
    @GeneratedValue
    @Column(name="city_id")
    private Long id;

    private String name;

    private String cityLink;

    @JsonManagedReference
    @OneToMany(mappedBy = "city",cascade = CascadeType.ALL)
    private List<PlaceEn> placeList=new ArrayList<>();

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province_id")
    private ProvinceEn province;

    @JsonManagedReference
    @OneToMany(mappedBy = "city",cascade = CascadeType.ALL)
    private List<Station> stationList =new ArrayList<>();


}
