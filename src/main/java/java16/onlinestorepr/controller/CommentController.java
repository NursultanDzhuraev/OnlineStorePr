package java16.onlinestorepr.controller;

import java16.onlinestorepr.dto.request.CommentRequest;
import java16.onlinestorepr.dto.response.CommentResponse;
import java16.onlinestorepr.dto.response.PaginationResponse;
import java16.onlinestorepr.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/save/{productId}/{userId}")
    public ResponseEntity<?> saveComment(@PathVariable Long productId,
                                         @PathVariable Long userId,
                                         @RequestBody CommentRequest commentRequest) {
      return commentService.createdComment(productId, userId, commentRequest);
    }

    @PutMapping("/update/{commentId}")
    public String update(@PathVariable Long commentId, @RequestBody CommentRequest commentRequest) {
        return commentService.update(commentId,commentRequest);
    }

    @GetMapping("/findAll/{productId}")
    public PaginationResponse<CommentResponse> findAll(@PathVariable Long productId,
                                         @RequestParam(defaultValue = "1") int pageNumber,
                                          @RequestParam(defaultValue = "2") int pageSize) {
        return commentService.findAll(productId,pageNumber,pageSize);
    }


    @PostMapping("/delete/{commentId}")
    public String update(@PathVariable Long commentId) {
        return commentService.delete(commentId);
    }

}
