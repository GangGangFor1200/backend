package ganggang3.gang.domain_en;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class CategoryEn {

    @Id
    @GeneratedValue
    @Column(name = "category_en_id")
    private Long id;

    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private List<PlaceEn> placeList=new ArrayList<>();
}