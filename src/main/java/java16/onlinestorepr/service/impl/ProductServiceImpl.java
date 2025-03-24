package java16.onlinestorepr.service.impl;



import java16.onlinestorepr.dto.request.ProductRequest;
import java16.onlinestorepr.dto.response.*;
import java16.onlinestorepr.emum.Category;
import java16.onlinestorepr.model.Product;
import java16.onlinestorepr.repo.ProductRepository;
import java16.onlinestorepr.repo.jdbc.ProductJdbc;
import java16.onlinestorepr.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
  private final ProductJdbc productJdbc;


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

}
