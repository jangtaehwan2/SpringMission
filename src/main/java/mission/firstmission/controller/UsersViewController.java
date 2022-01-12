package mission.firstmission.controller;

import lombok.RequiredArgsConstructor;
import mission.firstmission.domain.users.Users;
import mission.firstmission.domain.users.dto.UsersSignInDto;
import mission.firstmission.domain.users.dto.UsersSignUpDto;
import mission.firstmission.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class UsersViewController {
    private final UsersService usersService;

    @GetMapping("/")
    public String index(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null ) {
            model.addAttribute("users", session.getAttribute("users"));
        }
        return "index";
    }

    @GetMapping("/signin")
    public String signinPage() {

        return "signin";
    }

    @PostMapping("/users/signin")
    public String signin(@RequestParam (name = "name") String name
            , @RequestParam (name = "pw") String pw
            , HttpServletRequest request
                         ) {
        Users users = usersService.signIn(UsersSignInDto.builder()
                .name(name)
                .pw(pw)
                .build());
        if(users != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("users", users);
        }
        return "redirect:/";
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

    @PostMapping("/users/signup")
    public String signup(@RequestParam (name="name")String name, @RequestParam (name="pw")String pw) {
        usersService.signUp(UsersSignUpDto.builder()
                .name(name)
                .pw(pw)
                .build());
        return "redirect:/";
    }

    @GetMapping("/list")
    public String usersList(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session == null) {
            return "redirect:/";
        }
        List<Users> users = usersService.signUpUsers();
        model.addAttribute("users", users);
        return "list";
    }
}
