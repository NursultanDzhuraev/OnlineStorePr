package java16.onlinestorepr.repo;

import java16.onlinestorepr.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("select c from Comment c where c.product.id= :id")
    Page<Comment> findByProductId(Long id, Pageable pageable);
}
