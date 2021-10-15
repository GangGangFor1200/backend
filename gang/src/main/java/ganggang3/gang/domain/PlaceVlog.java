package ganggang3.gang.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class PlaceVlog {

    @Id
    @GeneratedValue
    @Column(name = "place_vlog_id")
    private Long id;


    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private PlaceEn place;


    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vlog_id")
    private VlogEn vlog;

    public void setPlace(PlaceEn place){
        this.place=place;
        place.getPlace_vlogList().add(this);
    }

    public void setVlog(VlogEn vlog){
        this.vlog=vlog;
        vlog.getPlace_vlogList().add(this);
    }

    public static PlaceVlog createPlace_Vlog(PlaceEn place, VlogEn vlog){
        PlaceVlog place_vlog=new PlaceVlog();
        place_vlog.setPlace(place);
        place_vlog.setVlog(vlog);
        return place_vlog;
    }


}
