package Fasion.backend.dto;


import Fasion.backend.domain.Post;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Data
public class PostCreateDto {
    private String title;
    private String content;
    private String author;
    private MultipartFile image1; // 파일 경로를 문자열로 받음
    private MultipartFile image2;
    private MultipartFile image3;

    public Post toEntity(String imagePath1, String imagePath2, String imagePath3) throws IOException {
        if (image1 == null) {
            throw new IllegalArgumentException("사진 한장은 필수 입니다.");
        }
        return Post.builder()
                .title(title)
                .content(content)
                .author(author)
                .image1(imagePath1)  // 파일 경로를 엔티티에 설정
                .image2(imagePath2)
                .image3(imagePath3)
                .build();
    }


}
