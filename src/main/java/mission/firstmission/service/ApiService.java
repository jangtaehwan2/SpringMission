package mission.firstmission.service;

import lombok.RequiredArgsConstructor;
import mission.firstmission.client.SearchApiClient;
import mission.firstmission.domain.search.dto.SearchResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ApiService {

    private final SearchApiClient searchApiClient;

    @Transactional
    public SearchResponseDto searchKeyword(String keyword) {
        return searchApiClient.requestSearch(keyword);
    }

}
