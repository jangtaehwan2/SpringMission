package mission.firstmission.domain.posts.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mission.firstmission.domain.posts.Posts;

@Getter
@NoArgsConstructor
public class PostsCreateDto {

    private String title;
    private String content;

    @Builder
    public PostsCreateDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Posts toEntity() {
        Posts posts = Posts.builder()
                .title(title)
                .content(content)
                .build();
        return posts;
    }
}
