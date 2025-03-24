package java16.onlinestorepr.repo.jdbc;

import java16.onlinestorepr.dto.response.ProductResponse;

import java.util.List;

public interface FavoriteJdbc {
    String toggleFavorite(Long userId, Long productId);

    List<ProductResponse> getFavoriteProducts(Long userId);
}
