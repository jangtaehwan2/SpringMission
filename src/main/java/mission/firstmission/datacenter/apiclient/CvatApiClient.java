package mission.firstmission.datacenter.apiclient;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import mission.firstmission.domain.cvat.dto.LoginDto;
import mission.firstmission.domain.user.User;
import mission.firstmission.domain.user.dto.RegisterDto;
import mission.firstmission.domain.user.dto.UsersResponseDto;
import mission.firstmission.domain.users.dto.UsersSignInDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class CvatApiClient {

    private final RestTemplate restTemplate;

    private final String signingUrl = "http://localhost:8080/api/v1/auth/signing";

    private final String apiUrl = "http://localhost:8080/api/v1/users?search=Django";

    private final String loginUrl = "http://localhost:8080/api/v1/auth/login";

    private final String registerUrl = "http://localhost:8080/api/v1/auth/register";

    private HttpHeaders headers;
    private String loginKey;

    public String authUrl() {
        HashMap<String, Object> serverUrl = new HashMap<>();
        serverUrl.put("url", "localhost:3000");
        return restTemplate.postForObject(signingUrl, serverUrl, String.class);
    }

    public RegisterDto requestRegister(RegisterDto registerDto) {
        HashMap<String, Object> registerInfo = new HashMap<>();
        registerInfo.put("username", registerDto.getUsername());
        registerInfo.put("first_name", registerDto.getFirst_name());
        registerInfo.put("last_name", registerDto.getLast_name());
        registerInfo.put("password1", registerDto.getPassword1());
        registerInfo.put("password2", registerDto.getPassword2());
        registerInfo.put("email", registerDto.getEmail());
        return restTemplate.postForObject(registerUrl, registerInfo, RegisterDto.class);
    }

    public LoginDto requestLogin(LoginDto loginDto) {

        HttpHeaders header = new HttpHeaders();

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

        body.add("username", loginDto.getUsername());
        body.add("password", loginDto.getPassword());

        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, header);

        return restTemplate.postForObject(loginUrl, entity, LoginDto.class);
    }

    public UsersResponseDto requestUsersList() {
        return restTemplate.getForObject(apiUrl, UsersResponseDto.class);
    }

    public String requestUsersSelf(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        System.out.println(cookies);
        HttpHeaders header = new HttpHeaders();
        header.add("Cookie", Arrays.toString(cookies));
        header.add("X-CSRFTOKEN", key);
        System.out.println(header.toString());
        HttpEntity entity = new HttpEntity(null, header);

        return restTemplate.exchange("http://localhost:8080/api/v1/users/self", HttpMethod.GET, entity, String.class).toString();
    }

    public LoginDto cvatLogin(UsersSignInDto user) {
        HttpHeaders header = new HttpHeaders();

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

        body.add("username", user.getName());
        body.add("password", user.getPassword());

        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, header);

//        return restTemplate.postForObject(loginUrl, entity, LoginDto.class);

        HttpEntity<LoginDto> response = restTemplate.exchange(loginUrl, HttpMethod.POST, entity, LoginDto.class);

        this.headers = response.getHeaders();
        LoginDto responseBody = response.getBody();
        this.loginKey = responseBody.getKey();
        return responseBody;
    }

    public User getSelfUser() {
        String userURL = "http://localhost:8080/api/v1/users/self";
        List<String> setCookieList = this.headers.get(HttpHeaders.SET_COOKIE);

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set(HttpHeaders.AUTHORIZATION, "Token " + this.loginKey);
        if (setCookieList != null) {
            requestHeaders.addAll(HttpHeaders.SET_COOKIE, setCookieList);
        }

        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(null, requestHeaders);
        HttpEntity<User> response = restTemplate.exchange(userURL, HttpMethod.GET, entity, User.class);
        return response.getBody();
    }
}
