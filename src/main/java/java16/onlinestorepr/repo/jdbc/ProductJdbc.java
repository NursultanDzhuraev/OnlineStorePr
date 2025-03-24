package java16.onlinestorepr.repo.jdbc;

import java16.onlinestorepr.dto.response.PaginationResponse;
import java16.onlinestorepr.dto.response.ProductResponse;
import java16.onlinestorepr.dto.response.ProductResponseAndComment;
import java16.onlinestorepr.dto.response.ProductResponseAndLike;

public interface ProductJdbc {
    ProductResponseAndComment getProductByIdAndComment(Long productId);

    PaginationResponse<ProductResponse> getProductWithCategoryAndPrice(String category, String ascOrDesc, int pageNumber, int pageSize);

    ProductResponseAndLike getProductByIdCountLike(Long productId);
}
