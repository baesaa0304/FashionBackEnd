package Fasion.backend.web;


import Fasion.backend.domain.Post;
import Fasion.backend.dto.PostCreateDto;
import Fasion.backend.dto.PostUpdateDto;
import Fasion.backend.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    @GetMapping({"/details"})
    public void readDetail(Long postId){

        postService.readDetail(postId);
    }

    /**
     * POST 글 수정하기
     */
    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody PostUpdateDto dto) {
        log.info("update(dto={})", dto);

        // 서비스 호출로 포스트 업데이트
        postService.update(dto);

        // 수정된 포스트의 ID를 포함하여 응답
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
