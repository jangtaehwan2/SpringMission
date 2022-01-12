package mission.firstmission.domain.users.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mission.firstmission.domain.users.Users;

@Getter
@NoArgsConstructor
public class UsersSignUpDto {

    private String name;
    private String pw;

    @Builder
    public UsersSignUpDto(String name, String pw) {
        this.name = name;
        this.pw = pw;
    }

    public Users toEntity() {
        Users user = Users.builder()
                .name(name)
                .pw(pw)
                .build();
        return user;
    }
}
