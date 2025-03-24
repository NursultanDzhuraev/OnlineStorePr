package java16.onlinestorepr.dto.response;

import java16.onlinestorepr.emum.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ProductResponseAndComment {
   private ProductResponse productResponse;
    private List<CommentResponse> comments;
}
