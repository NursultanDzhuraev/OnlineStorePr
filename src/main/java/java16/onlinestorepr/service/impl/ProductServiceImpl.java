package java16.onlinestorepr.service.impl;


import java16.onlinestorepr.dto.request.ProductRequest;
import java16.onlinestorepr.dto.response.*;
import java16.onlinestorepr.emum.Category;
import java16.onlinestorepr.exceptions.NotFoundException;
import java16.onlinestorepr.model.Brand;
import java16.onlinestorepr.model.Product;
import java16.onlinestorepr.repo.BrandRepository;
import java16.onlinestorepr.repo.ProductRepository;
import java16.onlinestorepr.repo.jdbc.ProductJdbc;
import java16.onlinestorepr.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductJdbc productJdbc;
    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;


    @Override
    public PaginationResponse<ProductResponse> getProductWithCategoryAndPrice(String category, String ascOrDesc, int pageNumber, int pageSize) {
        return productJdbc.getProductWithCategoryAndPrice(category, ascOrDesc, pageNumber, pageSize);
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
        Brand brand1 = brandRepository.findByBrandName(productRequest.getBrand().getBrandName())
                .stream()
                .findFirst()
                .orElse(null);

       if (brand1 == null) {
           Brand brand = new Brand();
           brand.setBrandName(productRequest.getBrand().getBrandName());
           brand.setImage(productRequest.getBrand().getImageUrl());
          Brand save =  brandRepository.save(brand);
          product.setBrand(save);
       }else {
           product.setBrand(brand1);
       }
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

    @Override
    public ProductResponse updated(Long productId, ProductRequest productRequest) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found"));
        Brand brand = brandRepository.findById(product.getBrand().getId())
                .orElseThrow(() -> new NotFoundException("Brand not found"));
        brand.setBrandName(productRequest.getBrand().getBrandName());
        brand.setImage(productRequest.getBrand().getImageUrl());
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setImages(productRequest.getImages());
        product.setCharacteristic(productRequest.getCharacteristic());
        product.setMadel(productRequest.getMadel());
        product.setCategory(Category.valueOf(productRequest.getCategory()));
        Product save = productRepository.save(product);
        return ProductResponse.from(save);
    }

    @Override
    public ResponseEntity<?> delete(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found"));
        productRepository.delete(product);
        return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted product");

    }


}
