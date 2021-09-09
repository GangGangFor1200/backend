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
public class Station {

    @Id
    @GeneratedValue
    @Column(name="statuon_id")
    private Long id;

    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "station",cascade = CascadeType.ALL)
    private List<Place> placeList=new ArrayList<>();

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province_id")
    private Province province;

}
