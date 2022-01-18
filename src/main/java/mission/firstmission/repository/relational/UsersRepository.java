package mission.firstmission.repository.relational;

import mission.firstmission.domain.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {

    Users findByName(String name);
}
