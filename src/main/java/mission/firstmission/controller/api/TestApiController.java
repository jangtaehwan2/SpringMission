package mission.firstmission.controller.api;

import lombok.RequiredArgsConstructor;
import mission.firstmission.domain.cvat.dto.LoginDto;
import mission.firstmission.domain.search.dto.SearchResponseDto;
import mission.firstmission.domain.user.dto.RegisterDto;
import mission.firstmission.domain.user.dto.UsersResponseDto;
import mission.firstmission.service.ApiService;
import org.springframework.web.bind.annotation.*;

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
    public LoginDto requestLogin(@RequestBody LoginDto loginDto) {
        System.out.println(loginDto);
        LoginDto newLogin = LoginDto.builder()
                .username(loginDto.getUsername())
                .password(loginDto.getPassword())
                .email(loginDto.getEmail())
                .build();
        return apiService.requestLogin(newLogin);
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
    public LoginDto requestLoginTest2() {
        LoginDto loginDto = LoginDto.builder()
                .username("Django")
                .password("98470000")
                .email("")
                .build();
        return apiService.requestLogin(loginDto);
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
}
