package Fasion.backend.web.like;


import Fasion.backend.dto.like.LikeRequestDto;
import Fasion.backend.service.like.LikeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/likes")
public class LikeController {
    private final LikeService likeService;



}
