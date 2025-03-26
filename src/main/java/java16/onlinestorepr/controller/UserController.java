package java16.onlinestorepr.controller;


import java16.onlinestorepr.dto.request.UserRequest;
import java16.onlinestorepr.dto.response.CartResponse;
import java16.onlinestorepr.dto.response.PaginationResponse;
import java16.onlinestorepr.dto.response.UserResponse;
import java16.onlinestorepr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("userFavoriteFindAll/{userId}")
    public CartResponse findUserFavorites(@PathVariable Long  userId) {
        return userService.getCartItems(userId);
    }
//    @Secured("ADMIN")
    @PutMapping("/update/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody UserRequest userRequest) {
        return userService.update(userId,userRequest);
    }
    @GetMapping("/findAll")
    public PaginationResponse<UserResponse> findAllUser(@RequestParam(defaultValue = "1") int pageNumber,
                                                        @RequestParam(defaultValue = "1") int pageSize) {

        return userService.findAll(pageNumber,pageSize);
    }

    @GetMapping("/findById/{userId}")
    public UserResponse findById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }
//    @Secured("ADMIN")
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        return userService.deleted(userId);
    }
}
