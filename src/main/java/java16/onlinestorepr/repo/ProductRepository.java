package java16.onlinestorepr.repo;

import java16.onlinestorepr.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
//    @Query("select p from Product p where upper(p.category)= :category ORDER BY p.price =:ascOrDesc ")
//    Page<Product> findAllWithCategoryAndPrice( @Param("category")String category, @Param("ascOrDesc") String ascOrDesc, Pageable pageable);
}
