package Fasion.backend.dto;


import Fasion.backend.domain.Post;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Data
public class PostUpdateDto {
    private Long PostId;
    private String title;
    private String content;
    private MultipartFile image1;
    private MultipartFile  image2;
    private MultipartFile  image3;


}
