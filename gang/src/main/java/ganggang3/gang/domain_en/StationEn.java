package ganggang3.gang.domain_en;

import com.fasterxml.jackson.annotation.JsonBackReference;
import ganggang3.gang.domain.City;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class StationEn {


    @Id
    @GeneratedValue
    @Column(name="station_en_id")
    private Long id;


    private String name;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_en_id")
    private CityEn city;



}
