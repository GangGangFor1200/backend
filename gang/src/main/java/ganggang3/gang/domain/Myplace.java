package ganggang3.gang.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Myplace {
    @Id
    @GeneratedValue
    @Column(name = "myplace_id")
    private Long id;

    private String name;

    private String category;

    private String explanation;

    private double locationx;
    private double locationy;

    @OneToMany(mappedBy = "myplace",cascade = CascadeType.ALL)
    private List<Myplace_Course> myplace_courseList=new ArrayList<>();

}
