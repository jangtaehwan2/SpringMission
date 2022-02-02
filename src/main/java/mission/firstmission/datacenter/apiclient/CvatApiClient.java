package mission.firstmission.datacenter.apiclient;

import lombok.RequiredArgsConstructor;
import mission.firstmission.domain.cvat.dto.LoginDto;
import mission.firstmission.domain.user.User;
import mission.firstmission.domain.users.dto.UsersSignInDto;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CvatApiClient {

    private final RestTemplate restTemplate;

    private final String loginUrl = "http://localhost:8080/api/v1/auth/login";

    private String loginKey;

    public ResponseEntity<LoginDto> cvatLogin(UsersSignInDto user, HttpServletRequest request) {
        HttpHeaders header = new HttpHeaders();

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

        body.add("username", user.getName());
        body.add("password", user.getPassword());

        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, header);
        // 빈 헤더와 username, password 로 구성된 request Entity 를 구성

        HttpEntity<LoginDto> response = restTemplate.exchange(loginUrl, HttpMethod.POST, entity, LoginDto.class);
        // 제작한 request 로 response 리턴

        LoginDto responseBody = response.getBody();
        // response 의 바디만 가져와서 형식에 맞는 바디로 변경

        HttpHeaders responseHeader = response.getHeaders();

        ResponseEntity<LoginDto> responseEntity = new ResponseEntity<>(responseBody, responseHeader, HttpStatus.OK);

        this.loginKey = responseBody.getKey();
        // response 의 바디에서 키를 가져와서 위 키에 주입
        // 아래의 동작에서 이 키는 헤더에 넣는다.

        HttpSession session = request.getSession();
        session.setAttribute("key", responseBody.getKey());

        return responseEntity;
    }

    public String getSelfUser(HttpServletRequest request, HttpHeaders headers) {
        String userURL = "http://localhost:8080/api/v1/users/self";

        HttpSession session = request.getSession();
        Cookie[] cookies = request.getCookies();

        HttpHeaders requestHeaders = headers;
        requestHeaders.set(HttpHeaders.AUTHORIZATION, "Token " + session.getAttribute("key"));
        // 헤더 로그인 키 값을 인증, 토큰으로 넣고

        // requestHeaders.addAll(HttpHeaders.SET_COOKIE, setCookieList);
        // 헤더 쿠키 리스트가 비어있을 경우에는 // 들어갔다고 가정 됨
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(null, requestHeaders);
        HttpEntity<String> response = restTemplate.exchange(userURL, HttpMethod.GET, entity, String.class);
        return response.getBody();
    }

    public ResponseEntity<String> cvatLogout() {
        String logoutURL = "http://localhost:8080/api/v1/auth/logout";

        HttpHeaders requestHeader = new HttpHeaders();

        HttpEntity<String> requestEntity = new HttpEntity<>(requestHeader);
        HttpEntity<String> response = restTemplate.exchange(logoutURL, HttpMethod.POST, requestEntity, String.class);

        HttpHeaders responseHeader = response.getHeaders();
        String body = response.getBody();

        ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.OK).headers(responseHeader).body(body);

        return responseEntity;
    }
}
