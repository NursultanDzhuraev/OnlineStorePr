package java16.onlinestorepr.controller;


import java16.onlinestorepr.dto.request.ProductRequest;
import java16.onlinestorepr.dto.response.PaginationResponse;
import java16.onlinestorepr.dto.response.ProductResponse;
import java16.onlinestorepr.dto.response.ProductResponseAndComment;
import java16.onlinestorepr.dto.response.ProductResponseAndLike;
import java16.onlinestorepr.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ProductRequest productRequest) {
        return productService.saveProduct(productRequest);
    }

    @PutMapping()



    @GetMapping("/findAll")
    public PaginationResponse<ProductResponse> getProductWithCategoryAndPrice(
            @RequestBody String category,
            @RequestBody String ascOrDesc,
            @RequestParam(defaultValue = "1") int pageNumber,
            @RequestParam(defaultValue = "1") int pageSize) {

        return productService.getProductWithCategoryAndPrice(category, ascOrDesc, pageNumber, pageSize);
    }


    @GetMapping("/findById/{productId}")
    public ProductResponseAndComment getProductById(@PathVariable Long productId) {
        return productService.getProductByIdAndComment(productId);
    }

    @GetMapping("findByIdCountLike/{productId}")
    public ProductResponseAndLike getProductByIdCountLike(@PathVariable Long productId) {
        return productService.getProductByIdCountLike(productId);
    }

}
