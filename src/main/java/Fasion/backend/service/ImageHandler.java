package Fasion.backend.service;


import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class ImageHandler {
    private final String uploadDir = "/Users/pearlinezero/uploads/";  // 파일이 저장될 경로

    public String saveImage(MultipartFile image , String existingImagePath) throws IOException {
        // 기존 이미지 파일이 있으면 삭제
        if (existingImagePath != null) {
            deleteImage(existingImagePath);
        }

        // 파일이 비어있으면 null을 반환
        if (image.isEmpty()) {
            return null;
        }
        // 이미지의 원래 파일 이름을 가져옴
        String fileName = getOriginName(image);
        // 저장할 파일 경로 생성
        Path filePath = Paths.get(uploadDir + fileName);

        // 업로드 디렉토리가 존재하지 않으면 생성
        if (!Files.exists(Paths.get(uploadDir))) {
            Files.createDirectories(Paths.get(uploadDir));
        }

        // 파일을 지정된 경로에 저장
        Files.write(filePath, image.getBytes());

        // 저장된 파일 경로를 문자열로 반환
        return filePath.toString();
    }

    private void deleteImage(String imagePath) throws IOException {
        Path path = Paths.get(imagePath);
        Files.deleteIfExists(path); // 파일이 존재하면 삭제
    }

    //원래 파일 이름 가져오기:
    private String getOriginName(MultipartFile image){
        return image.getOriginalFilename();
    }

}
