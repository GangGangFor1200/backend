package ganggang3.gang.domain_en;

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
public class CityEn {

    @Id
    @GeneratedValue
    @Column(name="city_en_id")
    private Long id;

    private String name;

    private String cityLink;

    @JsonManagedReference
    @OneToMany(mappedBy = "city_en",cascade = CascadeType.ALL)
    private List<PlaceEn> placeList=new ArrayList<>();

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province_en_id")
    private ProvinceEn province;

    @JsonManagedReference
    @OneToMany(mappedBy = "city_en",cascade = CascadeType.ALL)
    private List<StationEn> stationList =new ArrayList<>();


}
