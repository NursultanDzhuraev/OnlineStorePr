package java16.onlinestorepr.repo.jdbc.impl;

import java16.onlinestorepr.exceptions.NotFoundException;
import java16.onlinestorepr.repo.jdbc.BasketJdbc;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
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

    @Override
    public String toggleBasket(Long basketId, Long productId) {
        try {

         String checkSql = "SELECT COUNT(*) FROM basket_products WHERE basket_id = ? AND product_id = ?";
            Long count = jdbcClient.sql(checkSql)
                    .params(basketId, productId)
                    .query(Long.class)
                    .single();
            if (count > 0) {
                String deleteSql = "delete from basket_products where basket_id = ? and product_id = ?";
                jdbcClient.sql(deleteSql)
                        .params(basketId, productId)
                        .update();
                return "removed";
            } else {
                String insertSql = "insert into basket_products (basket_id, product_id) values (?, ?)";
                jdbcClient.sql(insertSql)
                        .params(basketId, productId)
                        .update();
                return "added";
            }
        }catch (Exception e){
            throw new NotFoundException(e.getMessage());
        }
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
