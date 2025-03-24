package java16.onlinestorepr.controller;


import java16.onlinestorepr.dto.request.UserRequest;
import java16.onlinestorepr.dto.response.CartResponse;
import java16.onlinestorepr.dto.response.UserResponse;
import java16.onlinestorepr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("userFavoriteFindAll/{userId}")
    public CartResponse findUserFavorites(@PathVariable Long  userId) {
        return userService.getCartItems(userId);
    }

//    @PostMapping
//    public UserResponse createUser(@RequestBody UserRequest request) {
//        return userService.saveUser(request);
//    }
//
//    @GetMapping("/{id}")
//    public UserResponse getUser(@PathVariable Long id) {
//        return userService.getUserById(id);
//    }
//
//    @GetMapping("/{id}/favorites")
//    public UserResponse getUserWithFavorites(@PathVariable Long id) {
//        return userService.getUserWithFavorites(id);
//    }
}
