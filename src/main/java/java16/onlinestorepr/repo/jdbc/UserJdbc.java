package java16.onlinestorepr.repo.jdbc;

import java16.onlinestorepr.dto.response.CartResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserJdbc {
    CartResponse getCartItems(Long userId);
}
