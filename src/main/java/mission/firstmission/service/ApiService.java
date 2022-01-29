package mission.firstmission.service;

import lombok.RequiredArgsConstructor;
import mission.firstmission.datacenter.apiclient.CvatApiClient;
import mission.firstmission.datacenter.apiclient.SearchApiClient;
import mission.firstmission.domain.cvat.dto.LoginDto;
import mission.firstmission.domain.search.dto.SearchResponseDto;
import mission.firstmission.domain.user.dto.RegisterDto;
import mission.firstmission.domain.user.dto.UsersResponseDto;
import mission.firstmission.manager.SessionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URI;

@RequiredArgsConstructor
@Service
public class ApiService {

    private final SearchApiClient searchApiClient;
    private final CvatApiClient cvatApiClient;
    private final SessionManager sessionManager;

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
    public LoginDto requestLogin(LoginDto loginDto, HttpSession requestSession, HttpServletResponse response) {
        LoginDto responseDto =  cvatApiClient.requestLogin(loginDto);
        String key = sessionManager.setKey(requestSession, responseDto.getKey());
        Cookie cookie = new Cookie("Authorization", key);

        response.addCookie(cookie);
        return responseDto;
    }

    @Transactional
    public String requestUsersSelf(HttpServletRequest request, String key) {
        return cvatApiClient.requestUsersSelf(request, key);
    }

}
