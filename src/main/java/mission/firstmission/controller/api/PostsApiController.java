package mission.firstmission.controller.api;

import lombok.RequiredArgsConstructor;
import mission.firstmission.domain.posts.Posts;
import mission.firstmission.domain.posts.dto.PostsCreateDto;
import mission.firstmission.domain.posts.dto.PostsDeleteDto;
import mission.firstmission.domain.posts.dto.PostsUpdateDto;
import mission.firstmission.service.PostsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    // posts c r u d
    @PostMapping("/api/posts")
    public Posts postsCreate(@RequestBody PostsCreateDto postsCreateDto) {
        return postsService.postsCreate(postsCreateDto);
    }

    @GetMapping("/api/posts/title/{title}")
    public List<Posts> postsReadByTitle(@PathVariable String title) {
        return postsService.postsReadByTitle(title);
    }

    @GetMapping("/api/posts/id/{id}")
    public Posts postsReadById(@PathVariable String id) {
        return postsService.postsReadById(id);
    }

    @GetMapping("/api/posts")
    public List<Posts> postsRead() {
        return postsService.postsRead();
    }

    @PutMapping("/api/posts")
    public Posts postsUpdate(@RequestBody PostsUpdateDto postsUpdateDto) {

        // postService.postsUpdate(id, title, content);

        return postsService.postsUpdate(postsUpdateDto);
    }

    @DeleteMapping("/api/posts")
    public boolean postsDelete(@RequestBody PostsDeleteDto postsDeleteDto) {
        return postsService.postsDelete(postsDeleteDto);
    }
}
