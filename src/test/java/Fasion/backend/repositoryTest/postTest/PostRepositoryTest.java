package Fasion.backend.repositoryTest.postTest;


import Fasion.backend.domain.Post;
import Fasion.backend.dto.PostCreateDto;
import Fasion.backend.dto.PostUpdateDto;
import Fasion.backend.repository.PostRepository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class PostRepositoryTest {
    @Autowired
    private PostRepository postRepository;


    @Test
    public void testDelete() {
        long count = postRepository.count(); // DB 테이블의 행의 개수(엔터티 개수)
        log.info("삭제 전 count = {}", count);

        postRepository.deleteById(11L);

        count = postRepository.count();
        log.info("삭제 후 count = {}", count);
    }


    /**
     * POST 수정
     */
    //@Test
//    public void testUpdate() {
//        Post entity = postRepository.findById(1L).orElseThrow();
//        log.info("update 전 ; {}", entity);
//        log.info("update 전 수정 시간: {}", entity.getModifiedDateTime());
//
//        PostUpdateDto dto = new PostUpdateDto();
//        dto.setTitle("JPA update 테스트");
//        dto.setContent("JPA Hibernate를 사용한 DB 테이블 업데이트");
//        dto.setImage1("Updated Title".getBytes());
//
//        // 엔더티를 수정
//        entity.update(dto);
//
//        postRepository.saveAndFlush(entity);
//    }


    /**
     * POST 리스트 찾기
     */
    //@Test
    public void testSelectAll() {
        List<Post> list = postRepository.findByOrderByPostIdDesc(); //PostRepository.find All();
        for (Post p : list) {
            log.info(p.toString());
        }
    }

    /**
     * POST 작성
     */
    //@Test
    public void testSave() {
        // DB 테이블에 insert할 레코드(엔터티)를 생성:
        Post entity = Post.builder()
                .title("JPA 테스트")
                .content("JPA 라이브러리를 사용한 INSERT")
                .author("user")
                .build();
        log.info("insert 전 : {}", entity);
        log.info("created : {}, modified : {}", entity.getCreatedDateTime(), entity.getModifiedDateTime());

        // DB 테이블에 insert (저장)
        postRepository.save(entity);
        log.info("insert 후 : {}", entity);
        log.info("created : {}, modified : {}", entity.getCreatedDateTime(), entity.getModifiedDateTime());

    }



}