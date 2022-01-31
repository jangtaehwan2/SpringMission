package mission.firstmission.datacenter;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
//import mission.firstmission.datacenter.repository.nosql.PostsRepository;
import mission.firstmission.datacenter.repository.relational.UsersRepository;
import mission.firstmission.domain.users.Users;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Getter
@RequiredArgsConstructor
@Component
public class DataCenter implements DataCenterInterface{
    private final UsersRepository usersRepository;
//    private final PostsRepository postsRepository;

    @Override
    public Optional<Users> getUsersById(Long id) {
        return usersRepository.findById(id);
    }
}
