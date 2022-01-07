package mission.firstmission.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mission.firstmission.domain.users.Users;

@Getter
public class UsersSignInDto {

    private final String name;
    private final String pw;

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
