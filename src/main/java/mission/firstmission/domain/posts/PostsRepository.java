package mission.firstmission.domain.posts;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PostsRepository extends MongoRepository<Posts, String> {

    List<Posts> findByTitle(String title);
    List<Posts> findAllById(String id);
}
