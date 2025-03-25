package java16.onlinestorepr.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    private String firstName;
    private String lastName;
    @Email
    private String email;
    @Size(min = 4, max = 50,message = " password from min 4 and max 50")
    private String password;
}
