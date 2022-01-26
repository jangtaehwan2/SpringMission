package mission.firstmission.datacenter.apiclient;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import mission.firstmission.domain.cvat.dto.LoginDto;
import mission.firstmission.domain.user.dto.RegisterDto;
import mission.firstmission.domain.user.dto.UsersResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class CvatApiClient {

    private final RestTemplate restTemplate;

    private final String signingUrl = "https://localhost:8080/api/v1/auth/signing";

    private final String apiUrl = "https://localhost:8080/api/v1/users?search=Django";

    private final String loginUrl = "https://localhost:8080/api/v1/auth/login";

    private final String registerUrl = "https://localhost:8080/api/v1/auth/register";

    private final String serverKey = "eyJ1c2VyX2lkIjoxLCJ1c2VybmFtZSI6ImRqYW5nbyJ9%3A1nCi52%3AZi5zg5GAPUQ7Q8ltNH-hbDNIzqZgnUYGhRlgths2_gg";

    public String authUrl() {
        HashMap<String, Object> serverUrl = new HashMap<>();
        serverUrl.put("url", "http://localhost:8080/api/v1/users/1");
        return restTemplate.postForLocation(signingUrl, serverUrl).toString();
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
        header.add("sign", serverKey);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

        body.add("username", loginDto.getUsername());
        body.add("password", loginDto.getPassword());
        body.add("email", loginDto.getEmail());

        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, header);

        return restTemplate.postForObject(loginUrl, entity, LoginDto.class);
    }

    public UsersResponseDto requestUsersList() {
        return restTemplate.getForObject(apiUrl, UsersResponseDto.class);
    }
}
