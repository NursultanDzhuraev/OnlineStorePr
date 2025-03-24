package java16.onlinestorepr.service;

import java16.onlinestorepr.dto.response.ProductResponse;

import java.util.List;

public interface FavoriteService {
    String toggleFavorite(Long userId, Long productId);

    List<ProductResponse> getFavoriteProducts(Long userId);
}
