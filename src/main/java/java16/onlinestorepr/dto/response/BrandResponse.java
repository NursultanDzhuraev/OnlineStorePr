package java16.onlinestorepr.dto.response;

import java16.onlinestorepr.model.Brand;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BrandResponse {
    private Long id;
    private String brandName;
    private String imageUrl;

    public static BrandResponse from(Brand brand){
     return    BrandResponse.builder()
                .id(brand.getId())
                .brandName(brand.getBrandName())
                .imageUrl(brand.getImage())
                .build();
    }
}
