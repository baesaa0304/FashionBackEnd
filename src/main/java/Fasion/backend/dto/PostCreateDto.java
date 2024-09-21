package Fasion.backend.dto;


import Fasion.backend.domain.Post;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Data
public class PostCreateDto {
    private String title;
    private String content;
    private String author;
    private MultipartFile image1; // 파일 경로를 문자열로 받음
    private MultipartFile image2;
    private MultipartFile image3;

    public Post toEntity() throws IOException {
        if (image1 == null) {
            throw new IllegalArgumentException("사진 한장은 필수 입니다.");
        }
        return Post.builder()
                .title(title)
                .content(content)
                .author(author)
                .image1(image1 != null ? image1.getBytes() : null)  // MultipartFile -> byte[] 변환
                .image2(image2 != null ? image2.getBytes() : null)
                .image3(image3 != null ? image3.getBytes() : null)
                .build();
    }


}
