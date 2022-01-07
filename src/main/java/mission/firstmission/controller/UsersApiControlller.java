package mission.firstmission.controller;

import lombok.RequiredArgsConstructor;
import mission.firstmission.domain.users.Users;
import mission.firstmission.dto.UsersSignInDto;
import mission.firstmission.dto.UsersSignUpDto;
import mission.firstmission.service.UsersService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UsersApiControlller {

    private final UsersService usersService;

    @PostMapping("/api/users/signup")
    public Long signUp(@RequestBody UsersSignUpDto signUpDto) {
        return usersService.signUp(signUpDto);
    }

    @PostMapping("/api/users/signin")
    public Users signIn(@RequestBody UsersSignInDto signInDto) {
        return usersService.signIn(signInDto);
    }
}

