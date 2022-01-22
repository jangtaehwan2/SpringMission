package mission.firstmission.client;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import mission.firstmission.domain.cvat.dto.LoginDto;
import mission.firstmission.domain.user.dto.UsersResponseDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@Service
public class CvatApiClient {

    private final RestTemplate restTemplate = new RestTemplate();

    private final String signingUrl = "https://localhost:8080/api/v1/auth/signing";

    private final String apiUrl = "https://localhost:8080/api/v1/users?search=Django";

    public String authUrl() {
        HashMap<String, Object> serverUrl = new HashMap<>();
        serverUrl.put("url", "https://localhost:3000");
        return restTemplate.postForLocation(signingUrl, serverUrl).toString();
    }

    public UsersResponseDto requestUsersList() {
        return restTemplate.getForObject(apiUrl, UsersResponseDto.class);
    }
}
