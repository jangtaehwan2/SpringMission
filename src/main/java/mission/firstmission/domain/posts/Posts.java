//package mission.firstmission.domain.posts;
//
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.data.mongodb.core.mapping.Field;
//
//import javax.persistence.Column;
//
//
//@NoArgsConstructor
//@Getter
//@Document("posts")
//public class Posts{
//
//    @Id
//    private String id;
//
//    private String title;
//
//    private String content;
//
//    @Builder
//    public Posts(String title, String content) {
//        this.title = title;
//        this.content = content;
//    }
//
//    public void update(String title, String content) {
//        this.title = title;
//        this.content = content;
//    }
//}
