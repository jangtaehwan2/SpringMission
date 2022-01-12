package mission.firstmission.controller;

import lombok.RequiredArgsConstructor;
import mission.firstmission.domain.users.Users;
import mission.firstmission.domain.users.dto.UsersSignInDto;
import mission.firstmission.domain.users.dto.UsersSignUpDto;
import mission.firstmission.service.UsersService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UsersService usersService;

    @PostMapping("/api/users/signup")
    public Long signUp(@RequestBody UsersSignUpDto usersSignUpDto) {
        return usersService.signUp(usersSignUpDto);
    }

    @PostMapping("/api/users/signin")
    public Users signIn(@RequestBody UsersSignInDto signInDto, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Users users = usersService.signIn(signInDto);
        session.setAttribute("users", users);
        return users;
    }

    @GetMapping("/api/users")
    public List<Users> signUpUsers(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session == null) {
            throw new RuntimeException("need login");
        }
        return usersService.signUpUsers();
    }
}

