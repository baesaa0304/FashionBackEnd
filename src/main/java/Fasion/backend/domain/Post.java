package Fasion.backend.domain;

import Fasion.backend.dto.PostUpdateDto;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity
@Table(name = "POSTS")
public class Post extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    private Integer postId;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "CONTENT", nullable = false)
    private String content;

    @Column(name = "AUTHOR" , nullable = false)
    private String author;

    @Lob
    @Column(name = "image_1", columnDefinition = "LONGBLOB", nullable = false)
    private byte[] image1;

    @Lob
    @Column(name = "IMAGE_2", columnDefinition = "LONGBLOB")
    private byte[] image2;

    @Lob
    @Column(name = "IMAGE_3", columnDefinition = "LONGBLOB")
    private byte[] image3;


}
