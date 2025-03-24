package java16.onlinestorepr.controller;

import java16.onlinestorepr.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/basket")
@RequiredArgsConstructor
public class BasketController {

    private final BasketService basketService;

    @DeleteMapping("/clear")
    public ResponseEntity<String> clearBasket(@RequestHeader("Authorization") String token) {
        String jwtToken = token.startsWith("Bearer ") ? token.substring(7) : token;

        boolean cleared = basketService.clearBasket(jwtToken);
        if (cleared) {
            return ResponseEntity.ok("Корзина тазаланды");
        } else {
            return ResponseEntity.badRequest().body("Корзина табылган жок же бош");
        }
    }
}