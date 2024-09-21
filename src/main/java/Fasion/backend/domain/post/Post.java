package Fasion.backend.domain.post;

import Fasion.backend.domain.BaseTimeEntity;
import Fasion.backend.dto.post.PostUpdateDto;
import Fasion.backend.service.ImageHandler;
import jakarta.persistence.*;
import lombok.*;

import java.io.IOException;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity
@Table(name = "POSTS")
public class Post extends BaseTimeEntity {
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


    @Column(name = "image_1", nullable = false)
    private String image1;

    @Column(name = "IMAGE_2")
    private String image2;

    @Column(name = "IMAGE_3")
    private String image3;

    public Post update(PostUpdateDto dto , ImageHandler imageHandler) throws IOException {
        // DTO의 제목이 null이 아닐 경우 현재 엔티티의 제목을 업데이트
        if (dto.getTitle() != null) {
            this.title = dto.getTitle();
        }

        // DTO의 내용이 null이 아닐 경우 현재 엔티티의 내용을 업데이트
        if (dto.getContent() != null) {
            this.content = dto.getContent();
        }

        // 이미지 업데이트 처리
        if (dto.getImage1() != null) {
            // 새로운 이미지가 업로드된 경우
            this.image1 = imageHandler.saveImage(dto.getImage1() , this.image1); // 이미지1 파일 경로 저장
        }
        if (dto.getImage2() != null) {
            // 새로운 이미지가 업로드된 경우
            this.image2 = imageHandler.saveImage(dto.getImage2(), this.image2); // 이미지2 파일 경로 저장
        }
        if (dto.getImage3() != null) {
            // 새로운 이미지가 업로드된 경우
            this.image3 = imageHandler.saveImage(dto.getImage3() , this.image3); // 이미지3 파일 경로 저장
        }


        // 변경된 엔티티를 반환
        return this;
    }
}
