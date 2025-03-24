package java16.onlinestorepr.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductResponseAndLike {
    private ProductResponse productResponse;
    private Long likeCount;
}
