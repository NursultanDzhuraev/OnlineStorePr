package java16.onlinestorepr.service.impl;

import java16.onlinestorepr.dto.request.CommentRequest;
import java16.onlinestorepr.dto.response.CommentResponse;
import java16.onlinestorepr.dto.response.PaginationResponse;
import java16.onlinestorepr.exceptions.NotFoundException;
import java16.onlinestorepr.model.Comment;
import java16.onlinestorepr.model.Product;
import java16.onlinestorepr.model.User;
import java16.onlinestorepr.repo.CommentRepository;
import java16.onlinestorepr.repo.ProductRepository;
import java16.onlinestorepr.repo.UserRepository;
import java16.onlinestorepr.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private final CommentRepository commentRepository;
    @Autowired
    private final UserRepository userRepository;

    public CommentServiceImpl(ProductRepository productRepository, CommentRepository commentRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<?> createdComment(Long productId,Long userId, CommentRequest commentRequest) {
//        String email = SecurityContextHolder.getContext().getAuthentication().getName();
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new NotFoundException("User not found"));
        User user = userRepository.findByIdOrElseThrow(userId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found"));
        Comment comment = new Comment();
        comment.setProduct(product);
        comment.setUser(user);
        comment.setComment(commentRequest.getComment());
        comment.setCreatedDate(LocalDateTime.now());
        commentRepository.save(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body("Comment created successfully");
    }

    @Override
    public String update(Long commentId, CommentRequest commentRequest) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Comment not found"));
        comment.setComment(commentRequest.getComment());
        commentRepository.save(comment);
        return "Successfully updated comment";
    }

    @Override
    public PaginationResponse<CommentResponse> findAll(Long productId, int pageNumber, int pageSize) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product not found"));
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
      Page<Comment> comments= commentRepository.findByProductId(product.getId(),pageable);
      var response = new PaginationResponse<CommentResponse>();
      response.setPageNumber(comments.getNumber()+1);
      response.setPageSize(comments.getSize());
      response.setTotalElements(comments.getTotalElements());
      response.setTotalPages(comments.getTotalPages());
      response.setContent(CommentResponse.ofList(comments.getContent()));
        return response;
    }

    @Override
    public String delete(Long commentId) {
        Comment commentNotFound = commentRepository.findById(commentId).orElseThrow(() -> new NotFoundException("Comment not found"));
        commentRepository.delete(commentNotFound);
        return "Deleted comment successfully";
    }
}
