package Fasion.backend.service.like;


import Fasion.backend.domain.like.Like;
import Fasion.backend.domain.member.Member;
import Fasion.backend.domain.post.Post;
import Fasion.backend.dto.like.LikeRequestDto;
import Fasion.backend.repository.LikeRepository;
import Fasion.backend.repository.MemberRepository;
import Fasion.backend.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void addLike(LikeRequestDto likeRequestDto) throws Exception {
        Member member = memberRepository.findById(likeRequestDto.getMemberId()).orElseThrow();
        Post post = postRepository.findById(likeRequestDto.getPostId()).orElseThrow();

        if(likeRepository.findByMemberAndPost(member, post).isPresent()){
            throw new Exception();
        }

        Like like = Like.builder()
                    .post(post)
                    .member(member)
                    .build();

        likeRepository.save(like);
    }

    @Transactional
    public void removeLike(LikeRequestDto likeRequestDto) {
        Member member = memberRepository.findById(likeRequestDto.getMemberId()).orElseThrow();
        Post post = postRepository.findById(likeRequestDto.getPostId()).orElseThrow();
        Like like = likeRepository.findByMemberAndPost(member, post).orElseThrow();
        likeRepository.delete(like);

    }

}
