package java16.onlinestorepr.repo.jdbc.impl;

import java16.onlinestorepr.repo.jdbc.BasketJdbc;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketJdbcImpl implements BasketJdbc {
    private final JdbcClient jdbcClient;

    @Override
    public boolean clearBasket(String jwtToken) {
        Long userId = extractUserIdFromToken();

        String findBasketSql = "select id from basket where user_id = ?";
        Optional<Long> optionalBasketId = jdbcClient.sql(findBasketSql)
                .param(userId)
                .query(Long.class)
                .optional();

        if (optionalBasketId.isEmpty()) {
            return false;
        }
        Long basketId = optionalBasketId.get();

        String deleteItemsSql = "delete from basket_product where basket_id = ?";
        int rowsAffected = jdbcClient.sql(deleteItemsSql)
                .param(basketId)
                .update();

        return rowsAffected > 0;
    }

    private Long extractUserIdFromToken() {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof Jwt) {
                Jwt jwt = (Jwt) principal;
                return Long.valueOf(jwt.getClaimAsString("id"));
            }
            throw new RuntimeException("Токенден колдонуучуну аныктоо мүмкүн эмес");
        }
}
