package java16.onlinestorepr.dto.response;

import java16.onlinestorepr.emum.Category;
import java16.onlinestorepr.model.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ProductResponse {
    private Long id;
    private String name;
    private Double price;
    private Category category;
    private List<String> images;
    private String madel;
    private String characteristic;

//    public static ProductResponse from(Product product) {
//        return ProductResponse.builder()
//                .id(product.getId())
//                .name(product.getName())
//                .price(product.getPrice())
//                .category(product.getCategory())
//                .images(product.getImages())
//                .characteristic(product.getCharacteristic())
//                .madel(product.getMadel())
//                .build();
//    }
//
//    public static List<ProductResponse> fromList(List<Product> products) {
//        return products.stream().map(ProductResponse::from).toList();
//    }
}
