package java16.onlinestorepr.repo.jdbc;

public interface BasketJdbc {
    boolean clearBasket(String jwtToken);

    String toggleBasket(Long userId, Long productId);
}
