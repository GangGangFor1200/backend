package ganggang3.gang.domain;

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

    @OneToMany(mappedBy = "province",cascade = CascadeType.ALL)
    private List<Station> stationList=new ArrayList<>();
}
