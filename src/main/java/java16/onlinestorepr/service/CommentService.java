package java16.onlinestorepr.service;

import java16.onlinestorepr.dto.request.CommentRequest;
import java16.onlinestorepr.dto.response.CommentResponse;
import java16.onlinestorepr.dto.response.PaginationResponse;
import org.springframework.http.ResponseEntity;

public interface CommentService {
    ResponseEntity<?> createdComment(Long productId,Long userId, CommentRequest commentRequest);

    String update(Long commentId, CommentRequest commentRequest);

    PaginationResponse<CommentResponse> findAll(Long productId, int page, int size);

    String delete(Long commentId);
}

