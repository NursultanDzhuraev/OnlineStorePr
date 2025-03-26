package java16.onlinestorepr.service;

import java16.onlinestorepr.dto.request.ProductRequest;
import java16.onlinestorepr.dto.response.PaginationResponse;
import java16.onlinestorepr.dto.response.ProductResponse;
import java16.onlinestorepr.dto.response.ProductResponseAndComment;
import java16.onlinestorepr.dto.response.ProductResponseAndLike;
import org.springframework.http.ResponseEntity;

public interface ProductService {


    PaginationResponse<ProductResponse> getProductWithCategoryAndPrice(String category, String ascOrDesc, int pageNumber, int pageSize);

    ProductResponseAndComment getProductByIdAndComment(Long productId);

    ProductResponseAndLike getProductByIdCountLike(Long productId);

    ResponseEntity<?> saveProduct(ProductRequest productRequest);

    ProductResponse updated(Long productId, ProductRequest productRequest);

    ResponseEntity<?> delete(Long productId);

}

