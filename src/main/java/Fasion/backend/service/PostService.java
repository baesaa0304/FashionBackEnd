package Fasion.backend.service;


import Fasion.backend.domain.Post;
import Fasion.backend.dto.PostCreateDto;
import Fasion.backend.dto.PostUpdateDto;
import Fasion.backend.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    /**
     * POST 글 작성하기
     */
    public Post create(PostCreateDto dto) throws IOException {
        Post entity = dto.toEntity();
        return postRepository.save(entity);
    }


    /**
     * POST_List 글 목록 찾아내기
     */
    @Transactional(readOnly = true)
    public List<Post> read(){
        log.info("read()");
        return postRepository.findByOrderByPostIdDesc();
    }
    /**
     * POST 글 상세보기
     */
    @Transactional(readOnly = true)
    public Post readDetail(Long postId) {
        return postRepository.findById(postId).orElseThrow();
    }

    /**
     * POST 글 수정(제목 , 내용 , 사진 수정 가능)
     */
//    @Transactional
//    public void update(PostUpdateDto dto) {
//        Post entity = postRepository.findById(dto.getPostId()).orElseThrow();
//        entity.update(dto);
//
//    }

    /**
     * POST 삭제
     */
    public void delete(Long id) {
        log.info("delete{}" , id);
        // 포스트 아이디를 잦아서 삭제를 함 deleteById는 void 값임
        postRepository.deleteById(id);

    }
}
