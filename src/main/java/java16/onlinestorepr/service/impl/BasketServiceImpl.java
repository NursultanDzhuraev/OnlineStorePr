package java16.onlinestorepr.service.impl;


import java16.onlinestorepr.exceptions.NotFoundException;
import java16.onlinestorepr.model.Basket;
import java16.onlinestorepr.model.Product;
import java16.onlinestorepr.model.User;
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

    @Override
    public boolean clearBasket(String jwtToken) {
        return basketJdbc.clearBasket(jwtToken);
    }

    @Override
    public String toggleBasket(Long userId, Long productId) {
     return basketJdbc.toggleBasket(userId, productId);
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new NotFoundException("User not found"));
//        if ( user.getBasket() == null) {
//            throw new NotFoundException(" user's basket not found");
//        }
//        Basket basket = user.getBasket();
//
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new NotFoundException("Product not found"));
//
//        List<Product> products = basket.getProducts();
//        if (products.contains(product)) {
//            products.remove(product);
//            return "removed";
//        } else {
//            products.add(product);
//            return "added";
//        }
    }

}
