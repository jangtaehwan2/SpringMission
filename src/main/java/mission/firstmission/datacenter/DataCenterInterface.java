package mission.firstmission.datacenter;

import mission.firstmission.domain.users.Users;

import java.util.Optional;

public interface DataCenterInterface {
    public Optional<Users> getUsersById(Long id);
}
