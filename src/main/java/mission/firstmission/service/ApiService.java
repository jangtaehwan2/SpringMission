package mission.firstmission.service;

import lombok.RequiredArgsConstructor;
import mission.firstmission.client.CvatApiClient;
import mission.firstmission.client.SearchApiClient;
import mission.firstmission.domain.search.dto.SearchResponseDto;
import mission.firstmission.domain.user.dto.UsersResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ApiService {

    private final SearchApiClient searchApiClient;
    private final CvatApiClient cvatApiClient;

    @Transactional
    public SearchResponseDto searchKeyword(String keyword) {
        return searchApiClient.requestSearch(keyword);
    }

    @Transactional
    public String authServer() { return cvatApiClient.authUrl(); }

    @Transactional
    public UsersResponseDto requestUserList() { return cvatApiClient.requestUsersList(); }

}
