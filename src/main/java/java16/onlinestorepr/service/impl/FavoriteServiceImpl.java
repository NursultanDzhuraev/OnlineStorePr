package java16.onlinestorepr.service.impl;

import java16.onlinestorepr.dto.response.ProductResponse;
import java16.onlinestorepr.repo.FavoriteRepository;
import java16.onlinestorepr.repo.jdbc.FavoriteJdbc;
import java16.onlinestorepr.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final FavoriteJdbc favoriteJdbc;

    @Override
    public String toggleFavorite(Long userId, Long productId) {
        return favoriteJdbc.toggleFavorite(userId,productId);
    }

    @Override
    public List<ProductResponse> getFavoriteProducts(Long userId) {
        return favoriteJdbc.getFavoriteProducts(userId);
    }
}
