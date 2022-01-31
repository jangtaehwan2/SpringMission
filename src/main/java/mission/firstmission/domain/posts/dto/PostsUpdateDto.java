//package mission.firstmission.domain.posts.dto;
//
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import mission.firstmission.domain.posts.Posts;
//
//@NoArgsConstructor
//@Getter
//public class PostsUpdateDto {
//
//    private String id;
//    private String title;
//    private String content;
//
//    @Builder
//    public PostsUpdateDto(String id, String title, String content) {
//        this.id = id;
//        this.title = title;
//        this.content = content;
//    }
//
//    public Posts toEntity() {
//        Posts posts = Posts.builder()
//                .title(title)
//                .content(content)
//                .build();
//        return posts;
//    }
//}
