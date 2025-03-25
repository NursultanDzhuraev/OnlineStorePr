package java16.onlinestorepr.service.impl;



import jakarta.annotation.PostConstruct;
import java16.onlinestorepr.config.JwtService;
import java16.onlinestorepr.dto.request.LoginRequest;
import java16.onlinestorepr.dto.request.UserRequest;
import java16.onlinestorepr.dto.response.AuthResponse;
import java16.onlinestorepr.dto.response.CartResponse;
import java16.onlinestorepr.dto.response.PaginationResponse;
import java16.onlinestorepr.dto.response.UserResponse;
import java16.onlinestorepr.emum.Role;
import java16.onlinestorepr.exceptions.NotFoundException;
import java16.onlinestorepr.model.User;
import java16.onlinestorepr.repo.UserRepository;
import java16.onlinestorepr.repo.jdbc.UserJdbc;
import java16.onlinestorepr.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserJdbc userJdbc;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public CartResponse getCartItems(Long userId) {
        return userJdbc.getCartItems(userId);
    }

    public String saveUser(UserRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setCreatedDate(LocalDateTime.now());
        user.setRole(Role.USER);
         userRepository.save(user);
        return "Saved successfully";
    }
    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepository.findByIdOrElseThrow(id);
        return UserResponse.fromUser(user);
    }

    @Override
    public ResponseEntity<?> login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        boolean matches = passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());
        if (!matches) {
            throw new BadCredentialsException("Incorrect password or email");
        }
        return   ResponseEntity.status(HttpStatus.CREATED)
                .body(AuthResponse.builder()
                        .token(jwtService.generateToken(user))
                        .email(user.getEmail())
                        .role(user.getRole())
                        .build());
    }

    @Override
    public ResponseEntity<?> update(Long userId, UserRequest userRequest) {
        User user = userRepository.findByIdOrElseThrow(userId);
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setUpdatedDate(LocalDateTime.now());
        userRepository.save(user);
        return ResponseEntity.ok(String.format("User updated successfully"));
    }

    @Override
    public PaginationResponse<UserResponse> findAll(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
        Page<User> users = userRepository.findAll(pageable);
        if (users.getTotalElements() == 0) {
            throw new NotFoundException("User not found");
        }
      var response = new PaginationResponse<UserResponse>();
        response.setTotalElements(users.getTotalElements());
        response.setPageNumber(users.getNumber()+1);
        response.setPageSize(users.getSize());
        response.setTotalPages(users.getTotalPages());
        response.setContent(UserResponse.fromListUsers(users.getContent()));
        return response;
    }

    @Override
    public ResponseEntity<?> deleted(Long userId) {
        User user = userRepository.findByIdOrElseThrow(userId);
        userRepository.delete(user);
        return ResponseEntity.status(HttpStatus.OK).body(String.format("User deleted successfully"));
    }

    @PostConstruct
    private void init() {
        String email = "admin@gmail.com";
        if (!userRepository.findByEmail(email).isPresent()) {
            User user = new User();
            user.setEmail(email);
            user.setFirstName("admin");
            user.setLastName("admin");
            user.setPassword(passwordEncoder.encode("admin123"));
            user.setCreatedDate(LocalDateTime.now());
            user.setRole(Role.ADMIN);
            userRepository.save(user);
        }
    }
}

