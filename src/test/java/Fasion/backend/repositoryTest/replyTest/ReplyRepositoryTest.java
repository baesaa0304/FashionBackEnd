package Fasion.backend.repositoryTest.replyTest;

import Fasion.backend.domain.post.Post;
import Fasion.backend.domain.reply.Reply;
import Fasion.backend.repository.PostRepository;
import Fasion.backend.repository.ReplyRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
public class ReplyRepositoryTest {

    @Autowired
    private ReplyRepository replyRepository;
    @Autowired
    private PostRepository postRepository;

    private Post testPost;

    @BeforeEach
    public void setup() {
        // 데이터베이스에서 ID가 13인 Post를 조회 (이미 존재하는 게시물)
        testPost = postRepository.findById(13L)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 게시물을 찾을 수 없습니다."));
    }



    //@Test
    public void testSaveReply() {
        // Given
        Reply reply = Reply.builder()
                .post(testPost)
                .replyText("댓글이다 ㅋ")
                .writer("소이")
                .build();

        // When
        Reply savedReply = replyRepository.save(reply);

        // Then
        assertThat(savedReply).isNotNull();
        assertThat(savedReply.getReplyId()).isNotNull();
        assertThat(savedReply.getReplyText()).isEqualTo("댓글이다 ㅋ");
        assertThat(savedReply.getWriter()).isEqualTo("소이");
        assertThat(savedReply.getPost()).isEqualTo(testPost);
    }

    //@Test
    public void testFindRepliesByPost() {
        // Given
        // 13번 게시물에 달린 댓글들 가져오기
        List<Reply> replies = replyRepository.findByPost(testPost);

        // When & Then
        assertThat(replies).isNotNull();
        assertThat(replies.size()).isGreaterThan(0);  // 댓글이 1개 이상 존재하는지 확인
        replies.forEach(reply -> {
            log.info("댓글 내용: {}, 작성자: {}", reply.getReplyText(), reply.getWriter());
        });
    }

    //@Test
    public void testCountRepliesByPost() {
        // 해당 게시물에 달린 댓글 수를 계산
        Long replyCount = replyRepository.countByPost(testPost);

        // 댓글 수 검증
        assertThat(replyCount).isEqualTo(2);  // 댓글 2개가 존재하는지 확인
        log.info("댓글 개수: {}", replyCount);
    }
}
