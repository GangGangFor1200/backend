package ganggang3.gang.dto;

import ganggang3.gang.domain.Place;
import ganggang3.gang.domain.Vlog;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VlogDto {
    private long id;
    private String name;
    private String url;

    public static VlogDto of (Vlog V){
        return new VlogDto(
                V.getId(),
                V.getName(),
                V.getUrl()
        );
    }
}

