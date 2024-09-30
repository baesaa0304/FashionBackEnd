package Fasion.backend.repository;

import Fasion.backend.domain.like.Like;
import Fasion.backend.domain.member.Member;
import Fasion.backend.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByMemberAndPost(Member member, Post post);
}
