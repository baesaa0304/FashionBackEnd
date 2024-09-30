package Fasion.backend.domain.reply;

import Fasion.backend.domain.BaseTimeEntity;
import Fasion.backend.domain.post.Post;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
@Entity
@Table(name = "REPLIES")
public class Reply extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REPLIES_ID")
    private Long replyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID", nullable = false)
    private Post post;

    @Column(name = "REPLY_TEXT", nullable = false)
    private String replyText;

    @Column(name = "WRITER",nullable = false)
    private String writer;

    public Reply update(String replyText) {
        this.replyText = replyText;

        return this;
    }
}
