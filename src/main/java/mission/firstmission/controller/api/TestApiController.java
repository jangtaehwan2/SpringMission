package mission.firstmission.controller.api;

import lombok.RequiredArgsConstructor;
import mission.firstmission.domain.cvat.dto.LoginDto;
import mission.firstmission.domain.search.dto.SearchResponseDto;
import mission.firstmission.domain.user.dto.RegisterDto;
import mission.firstmission.domain.user.dto.UsersResponseDto;
import mission.firstmission.service.ApiService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URI;

@RequiredArgsConstructor
@RestController
public class TestApiController {

    private final ApiService apiService;

    @GetMapping("/api/v1/search/{keyword}")
    public SearchResponseDto get(@PathVariable String keyword) {
        return apiService.searchKeyword(keyword);
    }

    @GetMapping("/api/v1/auth/server")
    public String authServer() {
        System.out.println("hello");
        return apiService.authServer();
    }

    @GetMapping("/api/v1/users")
    public UsersResponseDto requestUsersList() {
        return apiService.requestUserList();
    }

    @PostMapping("/api/v1/auth/login")
    public LoginDto requestLogin(@RequestBody LoginDto loginDto, HttpServletRequest request, HttpServletResponse response) {
        // true => session 없을 시 생성 / false => session 생성 x
        HttpSession session = request.getSession(true);
        return apiService.requestLogin(loginDto, session, response);
    }

    @GetMapping("/api/v1/auth/login/dto")
    public LoginDto requestLoginTest() {
        LoginDto loginDto = LoginDto.builder()
                .username("Django")
                .password("98470000")
                .build();
        return loginDto;
    }

    @GetMapping("/api/v1/auth/login/test")
    public LoginDto requestLoginTest2(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        LoginDto loginDto = LoginDto.builder()
                .username("Django")
                .password("98470000")
                .email("")
                .build();
        return apiService.requestLogin(loginDto, session, response);
    }

    @PostMapping("/api/v1/auth/register")
    public RegisterDto requestRegister(@RequestBody RegisterDto registerDto) {
        System.out.println(registerDto.getFirst_name());
        System.out.println(registerDto.getLast_name());
        System.out.println(registerDto.getUsername());
        System.out.println(registerDto.getEmail());
        System.out.println(registerDto.getPassword1());
        System.out.println(registerDto.getPassword2());
        return apiService.requestRegister(registerDto);
    }

    @GetMapping("/api/v1/users/self/{key}")
    public String requestUsersSelf(HttpServletRequest request, @PathVariable String key) {
        return apiService.requestUsersSelf(request, key);
    }


}
