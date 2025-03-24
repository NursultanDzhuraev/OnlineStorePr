package java16.onlinestorepr.controller;

import java16.onlinestorepr.dto.response.ProductResponse;
import java16.onlinestorepr.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    @PostMapping("/toggle")
    public ResponseEntity<String> toggleFavorite(@RequestParam Long userId, @RequestParam Long productId) {
        String result = favoriteService.toggleFavorite(userId, productId);
        if ("added".equals(result)) {
            return ResponseEntity.ok("added");
        } else if ("removed".equals(result)) {
            return ResponseEntity.ok("removed");
        } else {
            return ResponseEntity.badRequest().body("error");
        }
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ProductResponse>> getFavoriteProducts(@PathVariable Long userId) {
        List<ProductResponse> favorites = favoriteService.getFavoriteProducts(userId);
        return ResponseEntity.ok(favorites);
    }
}
