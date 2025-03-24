package java16.onlinestorepr.repo.jdbc.impl;

import java16.onlinestorepr.dto.response.CartItemResponse;
import java16.onlinestorepr.dto.response.CartResponse;
import java16.onlinestorepr.dto.response.ProductResponse;
import java16.onlinestorepr.emum.Category;
import java16.onlinestorepr.repo.jdbc.UserJdbc;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserJdbcImpl implements UserJdbc {

    private final JdbcClient jdbcClient;
    @Override
    public CartResponse getCartItems(Long userId) {
        String findBasketSql = "select id from basket where user_id = ?";
        Optional<Long> optionalBasketId = jdbcClient.sql(findBasketSql).param(userId).query(Long.class).optional();
        if (optionalBasketId.isEmpty()) {
            return CartResponse.builder()
                    .items(new ArrayList<>())
                    .totalItems(0)
                    .totalSum(0.0)
                    .build();
        }
        Long basketId = optionalBasketId.get();

        String getCartItemsSql = """
            select p.id, p.name, p.price, p.category, p.characteristic, p.madel, p.images,
                   bp.quantity
            from product p
            join basket bp ON p.id = bp.product_id
            where bp.basket_id = ?
            """;
        List<CartItemResponse> items = jdbcClient.sql(getCartItemsSql)
                .param(basketId)
                .query((rs, rowNum) -> {
                    ProductResponse productResponse = ProductResponse.builder()
                            .id(rs.getLong("id"))
                            .name(rs.getString("name"))
                            .price(rs.getDouble("price"))
                            .category(Category.valueOf(rs.getString("category")))
                            .characteristic(rs.getString("characteristic"))
                            .madel(rs.getString("madel"))
                            .images(Arrays.asList(rs.getString("images").split(",")))
                            .build();
                    int quantity = rs.getInt("quantity");
                    return CartItemResponse.builder()
                            .product(productResponse)
                            .quantity(quantity)
                            .build();
                })
                .list();

        int totalItems = items.stream().mapToInt(CartItemResponse::getQuantity).sum();
        double totalSum = items.stream().mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity()).sum();

        return CartResponse.builder()
                .items(items)
                .totalItems(totalItems)
                .totalSum(totalSum)
                .build();
    }
}
