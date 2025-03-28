package java16.onlinestorepr.service.impl;


import java16.onlinestorepr.exceptions.NotFoundException;
import java16.onlinestorepr.model.Basket;
import java16.onlinestorepr.model.Product;
import java16.onlinestorepr.model.User;
import java16.onlinestorepr.repo.BasketRepository;
import java16.onlinestorepr.repo.ProductRepository;
import java16.onlinestorepr.repo.UserRepository;
import java16.onlinestorepr.repo.jdbc.BasketJdbc;
import java16.onlinestorepr.repo.jdbc.ProductJdbc;
import java16.onlinestorepr.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {
    private final BasketJdbc basketJdbc;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final BasketRepository basketRepository;

    @Override
    public boolean clearBasket(String jwtToken) {
        return basketJdbc.clearBasket(jwtToken);
    }

    @Override
    public String toggleBasket(Long userId, Long productId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found"));

        Basket basket1 = user.getBasket();
        if (basket1==null) {
            Basket basket = new Basket();
            basket.setUser(user);
            basket.setProducts(List.of(product));
            basketRepository.save(basket);
            return "added to basket";
        }
        else if (!basket1.getProducts().contains(product)) {
            basket1.getProducts().add(product);
            basketRepository.save(basket1);
            return "added to basket";
        }
        basket1.getProducts().remove(product);
        basketRepository.save(basket1);
        return "deleted from basket";
    }

}
