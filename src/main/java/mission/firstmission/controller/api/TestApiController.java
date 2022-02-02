package mission.firstmission.controller.api;

import lombok.RequiredArgsConstructor;
import mission.firstmission.domain.cvat.dto.LoginDto;
import mission.firstmission.domain.search.dto.SearchResponseDto;
import mission.firstmission.domain.user.User;
import mission.firstmission.domain.user.dto.RegisterDto;
import mission.firstmission.domain.user.dto.UsersResponseDto;
import mission.firstmission.domain.users.dto.UsersSignInDto;
import mission.firstmission.service.ApiService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/api/login")
    public ResponseEntity<LoginDto> login(@RequestBody UsersSignInDto user, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.setAttribute("hello", "world!");
        return apiService.cvatLogin(user, request);
    }

    @GetMapping("/api/userinfo")
    public String getUserInfo(HttpServletRequest request, @RequestHeader HttpHeaders headers) {
        HttpSession session = request.getSession(false);
        System.out.print("##############################\nSESSION NAME IS ");
        System.out.println(session.getAttribute("hello"));
        System.out.println("##############################");
        return apiService.cvatSelfUser(request, headers);
    }

    @GetMapping("/api/login/{name}/{password}")
    public ResponseEntity login(@PathVariable String name, @PathVariable String password, HttpServletRequest request) {
        UsersSignInDto usersSignInDto = UsersSignInDto.builder()
                .name(name)
                .pw(password)
                .build();
        ResponseEntity<LoginDto> responseEntity =apiService.cvatLogin(usersSignInDto, request);
        HttpSession session = request.getSession(true);
        session.setAttribute("hello", name);
        return responseEntity;
    }

    @GetMapping("/api/logout")
    public ResponseEntity<String> logout(HttpServletResponse response, HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        Cookie[] cookies = request.getCookies();
        for(int i = 0; i < cookies.length; i++) {
            cookies[i].setMaxAge(0);
            response.addCookie(cookies[i]);
        }

        System.out.println("Good Bye : " + session.getAttribute("hello"));
        session.invalidate();
        return apiService.cvatLogout();
    }
}
