package Fasion.backend.web;


import Fasion.backend.domain.post.Post;
import Fasion.backend.dto.post.PostCreateDto;
import Fasion.backend.dto.post.PostUpdateDto;
import Fasion.backend.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;



    /**
     * POST 글 쓰기
     */
    @GetMapping("/create")
    public void create() {
        log.info("create() GET");
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@ModelAttribute PostCreateDto dto) throws IOException {
        // 서비스에서 데이터 생성
        Post createdPost = postService.create(dto);
        // 성공적인 생성 응답을 반환
        return ResponseEntity.ok("생성 완료 ID: " + createdPost.getPostId());
    }



    /**
     * POST 글 리스트 불러오기
     */
    @GetMapping("/read")
    public ResponseEntity<List<Post>> read() {
        List<Post> posts = postService.read();
        return ResponseEntity.ok(posts);
    }

    /**
     * POST 글 상세보기
     */
    @GetMapping("/details/{postId}")
    public ResponseEntity<Post> readDetail(@PathVariable Long postId) {
        Post post = postService.readDetail(postId);
        return ResponseEntity.ok(post);
    }

    /**
     * POST 글 수정하기
     */
    @PostMapping("/update")
    public ResponseEntity<String> update(@ModelAttribute PostUpdateDto dto) throws IOException{
        log.info("update(dto={})", dto);

        postService.update(dto);

        return ResponseEntity.ok("수정 완료 . ID: " + dto.getPostId());
    }

    /**
     *  POST 글 삭제하기
     */
    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam long postId) {
        log.info("delete(id={})", postId);

        // 서비스 호출로 포스트 삭제
        postService.delete(postId);

        // 삭제 후 응답
        return ResponseEntity.ok("ID " + postId + "삭제완료");
    }
}
