package Fasion.backend.service.reply;

import Fasion.backend.domain.post.Post;
import Fasion.backend.domain.reply.Reply;
import Fasion.backend.dto.reply.ReplyCreateDto;
import Fasion.backend.dto.reply.ReplyUpdateDto;
import Fasion.backend.repository.PostRepository;
import Fasion.backend.repository.ReplyRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final PostRepository postRepository;

    /**
     * 댓글 생성하기
     */
    public Reply create(ReplyCreateDto replyCreateDto) {
        log.info("create(dto={})" , replyCreateDto);
        Post post = postRepository.findById(replyCreateDto.getPostId()).orElseThrow();
        replyRepository.save(replyCreateDto.toEntity(post));
        return replyCreateDto.toEntity(post);
    }

    /**
     * 댓글 삭제
     */
    public void delete(long id) {
        log.info("delete(id={}" , id);

        // DB replies 테이블에서 ID(고유키)로 엔더티 삭제하기:
        replyRepository.deleteById(id);
    }


    /**
     * 댓글 수정
     */
    @Transactional
    public void update(long id, ReplyUpdateDto dto) {
        log.info("upate(dto={}" , dto);

        // 1. 댓글 아이디로 엔터티 DB에서 엔터티를 검색(select):
        Reply entity = replyRepository.findById(id).orElseThrow();

        // 2. 검색한 엔터티의 프로퍼티를 수정:
        entity.update(dto.getReplyText());
    }

    /**
     * 검색 및 가져오기
     */
    @Transactional(readOnly = true)
    public List<Reply> read(Post post){
        log.info("read(post={})" , post);

        List<Reply> list = replyRepository.findByPostOrderByReplyIdDesc(post);

        return list;
    }

    @Transactional(readOnly = true)
    public List<Reply> readId(long postId) {
        log.info("read(postId={})", postId);

        // 1. postId로 Post를 검색.
        Post post = postRepository.findById(postId).orElseThrow();

        // 2. 찾은 Post에 달려 있는 댓글 목록을 검색.
        List<Reply> list = replyRepository.findByPostOrderByReplyIdDesc(post);

        return list;
    }
    /**
    댓글 수
     */
    public Long countByPost(Post post) {
        log.info("countByPost(post={})" , post);

        return replyRepository.countByPost(post);
    }

}
