package mission.firstmission.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UsersResponseDto {
    private int count;
    private String next;
    private String previous;
    private User[] results;

    static class User {
        public String url;
        public int id;
        public String username;
        public String first_name;
        public String last_name;
//        public String email;
//        public String groups;
//        public boolean is_staff;
//        public boolean is_superuser;
//        public boolean is_active;
//        public String last_login;
//        public String date_joined;
    }

}
