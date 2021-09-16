package ganggang3.gang.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Province {
    @Id
    @GeneratedValue
    @Column(name="province_id")
    private Long id;

    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "province",cascade = CascadeType.ALL)
    private List<City> cityList =new ArrayList<>();


}
