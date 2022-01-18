package mission.firstmission.service;

import lombok.RequiredArgsConstructor;
import mission.firstmission.domain.posts.Posts;
import mission.firstmission.repository.nosql.PostsRepository;
import mission.firstmission.domain.posts.dto.PostsCreateDto;
import mission.firstmission.domain.posts.dto.PostsDeleteDto;
import mission.firstmission.domain.posts.dto.PostsUpdateDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Posts postsCreate(PostsCreateDto postsCreateDto) {
        return postsRepository.save(postsCreateDto.toEntity());
    }

    @Transactional
    public List<Posts> postsReadByTitle(String title) {
        List<Posts> postsList = postsRepository.findByTitle(title);
        return postsList;
    }

    @Transactional
    public Posts postsReadById(String id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("posts not found"));
        return posts;
    }

    @Transactional
    public List<Posts> postsRead() {
        return postsRepository.findAll();
    }

    @Transactional
    public Posts postsUpdate(PostsUpdateDto postsUpdateDto) {
        Posts posts = postsRepository.findById(postsUpdateDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("posts not found"));
        posts.update(postsUpdateDto.getTitle(), postsUpdateDto.getContent());
        postsRepository.save(posts);
        return posts;
    }

    @Transactional
    public boolean postsDelete(PostsDeleteDto postsDeleteDto) {
        Posts posts = postsRepository.findById(postsDeleteDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("posts not found"));
        postsRepository.delete(posts);
        return true;
    }
}
