package java16.onlinestorepr.service;

import java16.onlinestorepr.dto.request.UserRequest;
import java16.onlinestorepr.dto.response.CartResponse;
import java16.onlinestorepr.dto.response.UserResponse;
import java16.onlinestorepr.model.User;
import org.springframework.http.ResponseEntity;



public interface UserService {
    CartResponse getCartItems(Long userId);

//    UserResponse saveUser(UserRequest request);
//
//    UserResponse getUserById(Long id);
//
//    UserResponse getUserWithFavorites(Long id);
}

