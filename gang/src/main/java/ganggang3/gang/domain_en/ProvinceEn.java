package ganggang3.gang.domain_en;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import ganggang3.gang.domain.City;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class ProvinceEn {
    @Id
    @GeneratedValue
    @Column(name="province_en_id")
    private Long id;

    private String name;

    private String provinceLink;

    @JsonManagedReference
    @OneToMany(mappedBy = "province",cascade = CascadeType.ALL)
    private List<CityEn> cityList =new ArrayList<>();


}
