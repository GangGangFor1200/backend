package ganggang3.gang;

import lombok.Getter;

//토큰 갱신할 때 사용 안할거면 삭제

@Getter
public class RenewalAuth {

    private String access_token;
    private String token_type;
    private String refresh_token;
    private String refresh_token_expires_in;
    private String expires_in;
}
