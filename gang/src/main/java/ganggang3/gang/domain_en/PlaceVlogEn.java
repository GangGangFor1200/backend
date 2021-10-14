package ganggang3.gang.domain_en;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class PlaceVlogEn {

    @Id
    @GeneratedValue
    @Column(name = "place_vlog_en_id")
    private Long id;


    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_en_id")
    private PlaceEn place;


    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vlog_en_id")
    private VlogEn vlog;

    public void setPlace(PlaceEn place){
        this.place=place;
        place.getPlace_vlogList().add(this);
    }

    public void setVlog(VlogEn vlog){
        this.vlog=vlog;
        vlog.getPlace_vlogList().add(this);
    }

    public static PlaceVlogEn createPlace_Vlog(PlaceEn place, VlogEn vlog){
        PlaceVlogEn place_vlog=new PlaceVlogEn();
        place_vlog.setPlace(place);
        place_vlog.setVlog(vlog);
        return place_vlog;
    }


}
