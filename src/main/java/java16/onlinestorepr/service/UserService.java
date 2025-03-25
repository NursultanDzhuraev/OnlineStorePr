package java16.onlinestorepr.service;

import java16.onlinestorepr.dto.request.LoginRequest;
import java16.onlinestorepr.dto.request.UserRequest;
import java16.onlinestorepr.dto.response.CartResponse;
import java16.onlinestorepr.dto.response.PaginationResponse;
import java16.onlinestorepr.dto.response.UserResponse;
import org.springframework.http.ResponseEntity;



public interface UserService {
    CartResponse getCartItems(Long userId);

    String saveUser(UserRequest request);

    UserResponse getUserById(Long id);

    ResponseEntity<?> login(LoginRequest loginRequest);

    ResponseEntity<?> update(Long userId, UserRequest userRequest);

    PaginationResponse<UserResponse> findAll(int pageNumber, int pageSize);

    ResponseEntity<?> deleted(Long userId);
}

