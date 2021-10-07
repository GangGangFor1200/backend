package ganggang3.gang.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ganggang3.gang.AuthorizationKakao;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class KakaoService {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    private final String kakaoOauth2ClinetId = "4a6daba300b5fb1692604f6617982ed3";
    private final String frontendRedirectUrl = "http://localhost:8090";

    public AuthorizationKakao callTokenApi(String code) throws Exception {
        String grantType = "authorization_code";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", grantType);
        params.add("client_id", kakaoOauth2ClinetId);
        params.add("redirect_uri", frontendRedirectUrl + "/oauth/callback/kakao");
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        String url = "https://kauth.kakao.com/oauth/token";
        try {
            //토큰 요청
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
            //받은 Auth -> AuthorizationKakao에 연결
            AuthorizationKakao authorization = objectMapper.readValue(response.getBody(), AuthorizationKakao.class);

            return authorization;
        } catch (RestClientException | JsonProcessingException ex) {
            ex.printStackTrace();
            throw new Exception();
        }
    }


    /**
     * accessToken 을 이용한 유저정보 받기 -> id
     * @return
     */
    public String callGetUserByAccessToken(String accessToken) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        String url = "https://kapi.kakao.com/v2/user/me";
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
            // 값 리턴
            return response.getBody();
        }catch (RestClientException ex) {
            ex.printStackTrace();
            throw new Exception();
        }
    }

}
