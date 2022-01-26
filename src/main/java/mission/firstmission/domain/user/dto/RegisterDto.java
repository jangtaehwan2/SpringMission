package mission.firstmission.domain.user.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class RegisterDto {
    private String username;
    private String email;
    private String password1;
    private String password2;
    private String last_name;
    private String first_name;

}
