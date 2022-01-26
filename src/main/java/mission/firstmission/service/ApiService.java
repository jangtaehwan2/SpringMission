package mission.firstmission.service;

import lombok.RequiredArgsConstructor;
import mission.firstmission.datacenter.apiclient.CvatApiClient;
import mission.firstmission.datacenter.apiclient.SearchApiClient;
import mission.firstmission.domain.cvat.dto.LoginDto;
import mission.firstmission.domain.search.dto.SearchResponseDto;
import mission.firstmission.domain.user.dto.RegisterDto;
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
    public RegisterDto requestRegister(RegisterDto registerDto) {
        return cvatApiClient.requestRegister(registerDto);
    }

    @Transactional
    public UsersResponseDto requestUserList() { return cvatApiClient.requestUsersList(); }

    @Transactional
    public LoginDto requestLogin(LoginDto loginDto) {
        return cvatApiClient.requestLogin(loginDto);
    }

}
