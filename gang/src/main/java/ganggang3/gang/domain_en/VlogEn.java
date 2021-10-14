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
public class VlogEn {

    @Id
    @GeneratedValue
    @Column(name = "vlog_en_id")
    private Long id;

    private String name;

    private String url;

    @JsonManagedReference
    @OneToMany(mappedBy = "vlog_en",cascade = CascadeType.ALL)
    private List<PlaceVlogEn> place_vlogList=new ArrayList<>();


}