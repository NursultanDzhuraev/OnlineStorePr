package java16.onlinestorepr.dto.response;

import java16.onlinestorepr.emum.Role;
import lombok.Builder;

@Builder
public record AuthResponse(
        String token,
        String email,
        Role role) {
}
