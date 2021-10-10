package ganggang3.gang.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ganggang3.gang.AuthorizationKakao;
import ganggang3.gang.domain.Member;
import ganggang3.gang.exception.DatabaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
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

    private MemberService memberService;

    private final String kakaoOauth2ClinetId = "4a6daba300b5fb1692604f6617982ed3";
    private final String frontendRedirectUrl = "http://localhost:8090";

    //Token 받는 메소드
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
            throw new DatabaseException("로그인을 할 수 없습니다!");
        }
    }

    //유저정보 가져오는 메소드
    public String callGetUserByAccessToken(String access_token) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + access_token);
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
            throw new DatabaseException("로그인을 할 수 없습니다!");
        }
    }

    //로그아웃 요청하는 메소드
    public void logoutByAccessToken(String access_token) throws Exception{
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + access_token);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        String url = "https://kapi.kakao.com/v1/user/logout";
        try {
            restTemplate.postForEntity(url, request, String.class);
            // 값 리턴
        }catch (RestClientException ex) {
            ex.printStackTrace();
            throw new DatabaseException("이미 로그아웃 되었습니다!");
        }

    }

    //토큰 유효한지 확인하는 메소드
    public String isEnableToken(String access_token) throws Exception{
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + access_token);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        String url = "https://kapi.kakao.com/v1/user/access_token_info";
        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET,request,String.class);
            return access_token;
            // 값 리턴
        }catch (RestClientException ex) {
            ex.printStackTrace();
            //만료된 경우 다시 recall
            return recallTokenApi(access_token);
        }
    }

    //토큰 갱신하는 메소드
    public String recallTokenApi(String access_token) throws Exception{
        Member member=memberService.findById(access_token);
        String refresh_token=member.getRefresh_token();
        String grantType = "refresh_token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", grantType);
        params.add("client_id", kakaoOauth2ClinetId);
        params.add("refresh_token", refresh_token);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        String url = "https://kauth.kakao.com/oauth/token";
        try {
            //토큰 요청
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
            //받은 Auth -> AuthorizationKakao에 연결
            //AuthorizationKakao랑 필드가 좀 다른디 괜찮은가?
            AuthorizationKakao authorization = objectMapper.readValue(response.getBody(), AuthorizationKakao.class);
            //set을 하던가 아님 다시 createMember하면 아마 다시 저장될거임 일단 set으로
            member.setId(authorization.getAccess_token());
            member.setRefresh_token(authorization.getRefresh_token());
            return authorization.getAccess_token();
        } catch (RestClientException | JsonProcessingException ex) {
            ex.printStackTrace();
            throw new DatabaseException("토큰을 갱신할 수 없습니다!");
        }
    }

}
