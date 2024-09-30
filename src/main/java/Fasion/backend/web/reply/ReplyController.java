package Fasion.backend.web.reply;

import Fasion.backend.domain.reply.Reply;
import Fasion.backend.dto.reply.ReplyCreateDto;
import Fasion.backend.dto.reply.ReplyUpdateDto;
import Fasion.backend.service.reply.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reply")
public class ReplyController {
    private final ReplyService replyService;

    @PostMapping
    public ResponseEntity<Reply> create(@RequestBody ReplyCreateDto dto) {
        log.info("create(dto={})" , dto);

        // TOOD
        Reply reply = replyService.create(dto);
        log.info("reply={}" , reply);

        return ResponseEntity.ok(reply);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id){
        log.info("delete(id={})" , id);

        replyService.delete(id);

        return ResponseEntity.ok("Success");
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable long id, @RequestBody ReplyUpdateDto dto){
        log.info("update(id={})" , id);
        replyService.update(id, dto);

        return ResponseEntity.ok("Success");
    }

}
