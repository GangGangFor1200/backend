package ganggang3.gang.dto;

import ganggang3.gang.domain.VlogEn;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VlogDto {
    private long id;
    private String name;
    private String url;

    public static VlogDto of (VlogEn V){
        return new VlogDto(
                V.getId(),
                V.getName(),
                V.getUrl()
        );
    }
}

