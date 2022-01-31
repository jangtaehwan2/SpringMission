//package mission.firstmission.service;
//
//import lombok.RequiredArgsConstructor;
//import mission.firstmission.domain.posts.Posts;
//import mission.firstmission.datacenter.repository.nosql.PostsRepository;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@RequiredArgsConstructor
//@Service
//public class PostsService {
//
//    private final PostsRepository postsRepository;
//
//    @Transactional
//    public Posts postsCreate(String title, String content) {
//        Posts posts = Posts.builder()
//                .title(title)
//                .content(content)
//                .build();
//        return postsRepository.save(posts);
//    }
//
//    @Transactional
//    public List<Posts> postsReadByTitle(String title) {
//        List<Posts> postsList = postsRepository.findByTitle(title);
//        return postsList;
//    }
//
//    @Transactional
//    public Posts postsReadById(String id) {
//        Posts posts = postsRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("posts not found"));
//        return posts;
//    }
//
//    @Transactional
//    public List<Posts> postsRead() {
//        return postsRepository.findAll();
//    }
//
//    @Transactional
//    public Posts postsUpdate(String id, String title, String content) {
//        Posts posts = postsRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("posts not found"));
//        posts.update(title, content);
//        postsRepository.save(posts);
//        return posts;
//    }
//
//    @Transactional
//    public boolean postsDelete(String id) {
//        Posts posts = postsRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("posts not found"));
//        postsRepository.delete(posts);
//        return true;
//    }
//}
