package Fasion.backend.dto.reply;


import Fasion.backend.domain.post.Post;
import Fasion.backend.domain.reply.Reply;
import lombok.Data;

@Data
public class ReplyCreateDto {
    private Long postId;       // 댓글이 달릴 Post의 ID
    private String replyText;  // 댓글 내용
    private String writer;     // 댓글 작성자

    /**
     * 왜 값을 ? POST? -> 이유는 Reply 엔티티가 Post와의 관계를 맺고 있기 때문
     * @param post
     * @return
     */
    public Reply toEntity(Post post) {
        return Reply.builder()
                .post(post)               // Post 엔티티 설정
                .replyText(replyText)     // 댓글 내용 설정
                .writer(writer)           // 작성자 설정
                .build();                 // Reply 엔티티 빌드
    }
}
