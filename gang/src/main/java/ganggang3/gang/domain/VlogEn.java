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
public class VlogEn {

    @Id
    @GeneratedValue
    @Column(name = "vlog_id")
    private Long id;

    private String name;

    private String url;

    @JsonManagedReference
    @OneToMany(mappedBy = "vlog",cascade = CascadeType.ALL)
    private List<PlaceVlog> place_vlogList=new ArrayList<>();


}