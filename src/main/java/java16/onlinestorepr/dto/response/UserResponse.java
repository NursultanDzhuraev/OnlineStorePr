package java16.onlinestorepr.dto.response;


import java16.onlinestorepr.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Builder
public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    public static UserResponse fromUser(User user) {
      return   UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }
    public static List<UserResponse> fromListUsers(List<User> users) {
        return users.stream().map(UserResponse::fromUser).toList();
    }
}
