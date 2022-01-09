package mission.firstmission.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import mission.firstmission.domain.users.Users;

@Getter
@NoArgsConstructor
public class UsersSignInDto {

    private String name;
    private String pw;

    @Builder
    public UsersSignInDto(String name, String pw) {
        this.name = name;
        this.pw = pw;
    }

    public Users toEntity() {
        return Users.builder()
                .name(name)
                .pw(pw)
                .build();
    }
}
