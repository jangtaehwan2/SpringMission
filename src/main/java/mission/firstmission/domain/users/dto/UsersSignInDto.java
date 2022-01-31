package mission.firstmission.domain.users.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import mission.firstmission.domain.users.Users;

@Getter
@NoArgsConstructor
public class UsersSignInDto {

    private String name;
    private String password;

    @Builder
    public UsersSignInDto(String name, String pw) {
        this.name = name;
        this.password = pw;
    }

    public Users toEntity() {
        return Users.builder()
                .name(name)
                .pw(password)
                .build();
    }
}
