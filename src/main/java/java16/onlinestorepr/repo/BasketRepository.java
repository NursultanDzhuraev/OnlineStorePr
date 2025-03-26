package java16.onlinestorepr.repo;

import java16.onlinestorepr.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BasketRepository extends JpaRepository<Basket, Long> {
    @Query("select b from Basket b where b.user.id = :userId ")
    Basket findByUserId(Long userId);
}
