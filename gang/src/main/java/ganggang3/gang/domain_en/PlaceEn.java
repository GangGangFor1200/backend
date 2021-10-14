package ganggang3.gang.domain_en;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class PlaceEn {

    @Id
    @GeneratedValue
    @Column(name = "place_en_id")
    private Long id;

    private String name;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_en_id")
    private CityEn city;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_en_id")
    private CategoryEn category;

    private double location_x;
    private double location_y;

    private String phone;

    private String category_name;

    private String placeUrl;

    private String address;

    @OneToMany(mappedBy = "place",cascade = CascadeType.ALL)
    private List<PlaceVlogEn> place_vlogList=new ArrayList<>();

    //category와 station에도 추가되도록 setter만들기
    public void setCategory(CategoryEn category){
        this.category=category;
        category.getPlaceList().add(this);
    }
    public void setCity(CityEn city){
        this.city = city;
        city.getPlaceList().add(this);
    }

    public static PlaceEn of(PlaceVlogEn place_vlog) {
        return place_vlog.getPlace();
    }

}
