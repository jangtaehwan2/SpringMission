package mission.firstmission.controller.api;

import lombok.RequiredArgsConstructor;
import mission.firstmission.domain.search.dto.SearchResponseDto;
import mission.firstmission.domain.users.dto.UsersSignUpDto;
import mission.firstmission.service.ApiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TestApiController {

    private final ApiService apiService;

    @GetMapping("/api/v1/search/{keyword}")
    public SearchResponseDto get(@PathVariable String keyword) {
        return apiService.searchKeyword(keyword);
    }

}
