package java16.onlinestorepr.service;

public interface BasketService {
    boolean clearBasket(String jwtToken);

    String toggleBasket(Long userId, Long productId);

}
