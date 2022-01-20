package mission.firstmission.client;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import mission.firstmission.domain.search.dto.SearchResponseDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class SearchApiClient {
    private final RestTemplate restTemplate = new RestTemplate();

    private final String CLIENT_ID = "ryGvVUFL0n3PnjOW2goX";
    private final String CLIENT_SECRET = "4JoRE4EkFT";

    private final String SearchApiURL = "https://openapi.naver.com/v1/search/encyc.json?query={keyword}";

    public SearchResponseDto requestSearch(String keyword) {
        final HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", CLIENT_ID);
        headers.set("X-Naver-Client-Secret", CLIENT_SECRET);

        final HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(SearchApiURL, HttpMethod.GET, entity, SearchResponseDto.class, keyword).getBody();
    }
}
