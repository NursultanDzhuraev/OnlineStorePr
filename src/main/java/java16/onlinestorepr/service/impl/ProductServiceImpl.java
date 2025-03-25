package java16.onlinestorepr.service.impl;


import java16.onlinestorepr.dto.request.ProductRequest;
import java16.onlinestorepr.dto.response.*;
import java16.onlinestorepr.emum.Category;
import java16.onlinestorepr.model.Product;
import java16.onlinestorepr.repo.ProductRepository;
import java16.onlinestorepr.repo.jdbc.ProductJdbc;
import java16.onlinestorepr.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
  private final ProductJdbc productJdbc;
    private final ProductRepository productRepository;


    @Override
    public PaginationResponse<ProductResponse> getProductWithCategoryAndPrice(String category, String ascOrDesc, int pageNumber, int pageSize) {
        return productJdbc.getProductWithCategoryAndPrice(category,ascOrDesc,pageNumber,pageSize);
    }



    @Override
    public ProductResponseAndComment getProductByIdAndComment(Long productId) {
        return productJdbc.getProductByIdAndComment(productId);
    }

    @Override
    public ProductResponseAndLike getProductByIdCountLike(Long productId) {
        return productJdbc.getProductByIdCountLike(productId);
    }

    @Override
    public ResponseEntity<?> saveProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setImages(productRequest.getImages());
        product.setCharacteristic(productRequest.getCharacteristic());
        product.setMadel(productRequest.getMadel());
        product.setCategory(Category.valueOf(productRequest.getCategory()));
        product.setIsFavorite(false);
        productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body("Successfully saved product");
    }




}
