package mission.firstmission.controller.api;

import lombok.RequiredArgsConstructor;
import mission.firstmission.client.CvatApiClient;
import mission.firstmission.domain.cvat.dto.LoginDto;
import mission.firstmission.domain.search.dto.SearchResponseDto;
import mission.firstmission.domain.user.dto.UsersResponseDto;
import mission.firstmission.domain.users.dto.UsersSignUpDto;
import mission.firstmission.service.ApiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
        return apiService.authServer();
    }

    @GetMapping("/api/v1/users")
    public UsersResponseDto requestUsersList() {
        return apiService.requestUserList();
    }
}
