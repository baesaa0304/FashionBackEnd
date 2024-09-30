package Fasion.backend.repository;

import Fasion.backend.domain.post.Post;
import Fasion.backend.domain.reply.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    // 특정 Post의 ID로 댓글을 가져오는 메서드
    //List<Reply> findByPost(Post post);
    List<Reply> findByPostOrderByReplyIdDesc(Post post);


    // 특정 Post에 달린 댓글 개수 계산 메서드
    Long countByPost(Post post);

}
