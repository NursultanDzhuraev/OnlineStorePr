package java16.onlinestorepr.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class CartResponse {
    private List<CartItemResponse> items;
    private double totalSum;
    private int totalItems;

}
