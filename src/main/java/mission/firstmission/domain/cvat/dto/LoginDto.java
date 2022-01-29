package mission.firstmission.domain.cvat.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginDto {

    private String username;
    private String password;
    private String email;
    private String key;

    @Builder
    public LoginDto(String username, String password, String email, String key) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.key = key;
    }
}
