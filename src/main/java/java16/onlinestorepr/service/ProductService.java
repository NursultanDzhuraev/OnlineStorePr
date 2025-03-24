package java16.onlinestorepr.service;

import java16.onlinestorepr.dto.request.ProductRequest;
import java16.onlinestorepr.dto.response.PaginationResponse;
import java16.onlinestorepr.dto.response.ProductResponse;
import java16.onlinestorepr.dto.response.ProductResponseAndComment;
import java16.onlinestorepr.dto.response.ProductResponseAndLike;

public interface ProductService {
//    ProductResponse saveProduct(ProductRequest request);
//
//    ProductResponse getProductById(Long id);
//
//    ProductResponse getProductWithFavorites(Long id);
//
    PaginationResponse<ProductResponse> getProductWithCategoryAndPrice(String category, String ascOrDesc, int pageNumber, int pageSize);
//
//    ProductResponseAndComment getProductByIdAndComment(Long productId);

    ProductResponseAndComment getProductByIdAndComment(Long productId);

    ProductResponseAndLike getProductByIdCountLike(Long productId);
}

