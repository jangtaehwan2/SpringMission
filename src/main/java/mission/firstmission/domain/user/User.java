package mission.firstmission.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Getter
@NoArgsConstructor
public class User {
    private int id;
    private String url;
    private String username;
    private String email;
    private String first_name;
    private String last_name;
    private List<String> groups;
    private boolean is_staff;
    private boolean is_superuser;
    private boolean is_active;
    private String last_login;
    private String date_joined;

}
