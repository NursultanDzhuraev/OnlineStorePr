package java16.onlinestorepr.service.impl;



import jakarta.annotation.PostConstruct;
import java16.onlinestorepr.dto.request.UserRequest;
import java16.onlinestorepr.dto.response.CartResponse;
import java16.onlinestorepr.dto.response.ProductResponse;
import java16.onlinestorepr.dto.response.UserResponse;
import java16.onlinestorepr.emum.Role;
import java16.onlinestorepr.model.User;
import java16.onlinestorepr.repo.UserRepository;
import java16.onlinestorepr.repo.jdbc.UserJdbc;
import java16.onlinestorepr.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserJdbc userJdbc;
    @Override
    public CartResponse getCartItems(Long userId) {
        return userJdbc.getCartItems(userId);
    }

//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    // Жөнөкөй операция: JPA менен сактоо
//    public UserResponse saveUser(UserRequest request) {
//        User user = new User();
//        user.setFirstName(request.getFirstName());
//        user.setLastName(request.getLastName());
//        user.setEmail(request.getEmail());
//        user.setPassword(request.getPassword());
//        user.setCreatedDate(LocalDateTime.now());
//        User savedUser = userRepository.save(user);
//        return mapToUserResponse(savedUser);
//    }
//
//    // Жөнөкөй операция: JPA менен ID боюнча алуу
//    public UserResponse getUserById(Long id) {
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        return mapToUserResponse(user);
//    }
//
//    // Татаал сурам: JDBC Template менен колдонуучунун сүйүктүү товарлары менен маалыматын алуу
//    public UserResponse getUserWithFavorites(Long userId) {
//        String sql = "SELECT u.id, u.first_name, u.last_name, u.email, " +
//                "p.id AS product_id, p.name AS product_name, p.price, p.category " +
//                "FROM users u " +
//                "LEFT JOIN favorites f ON u.id = f.user_id " +
//                "LEFT JOIN product p ON f.product_id = p.id " +
//                "WHERE u.id = ?";
//
//        List<UserResponse> result = jdbcTemplate.query(sql, new Object[]{userId}, (rs, rowNum) -> {
//            UserResponse response = new UserResponse();
//            response.setId(rs.getLong("id"));
//            response.setFirstName(rs.getString("first_name"));
//            response.setLastName(rs.getString("last_name"));
//            response.setEmail(rs.getString("email"));
//
//            // Сүйүктүү товарларды кошуу
//            if (rs.getLong("product_id") != 0) {
//                ProductResponse product = null;
//                product.setId(rs.getLong("product_id"));
//                product.setName(rs.getString("product_name"));
//                product.setPrice(rs.getDouble("price"));
////                product.setCategory(rs.getString("category"));
//            }
//            return response;
//        });
//
//        if (result.isEmpty()) {
//            throw new RuntimeException("User not found");
//        }
//        return result.get(0); // Биринчи жыйынтыкты кайтарабыз
//    }
//
//    private UserResponse mapToUserResponse(User user) {
//        UserResponse response = new UserResponse();
//        response.setId(user.getId());
//        response.setFirstName(user.getFirstName());
//        response.setLastName(user.getLastName());
//        response.setEmail(user.getEmail());
//        return response;
//    }
//   @PostConstruct
//    private void init(){
//        String email = "admin@gmail.com";
//        User user1 = userRepository.findByEmail(email);
//        if(user1 == null){
//            User user = new User();
//            user.setEmail("admin@gmail.com");
//            user.setFirstName("admin");
//            user.setLastName("admin");
//            user.setPassword(passwordEncoder.encode("admin123"));
//            user.setCreatedDate(LocalDateTime.now());
//            user.setRole(Role.ADMIN);
//            userRepository.save(user);
//        }
//    }
}
