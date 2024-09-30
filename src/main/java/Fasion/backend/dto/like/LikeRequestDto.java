package Fasion.backend.dto.like;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter  @Setter
public class LikeRequestDto {
    private Long postId;
    private Long memberId;

    public LikeRequestDto(Long postId, Long memberId) {
        this.postId = postId;
        this.memberId = memberId;
    }

}
