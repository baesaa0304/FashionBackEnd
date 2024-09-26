package Fasion.backend.web;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class HomeController {
    /**
     * 메인 페이지
     * @return
     */
    @GetMapping
    public String home() {
        log.info("HOME");
        return "home";
    }
}
