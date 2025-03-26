package java16.onlinestorepr.dto.request;



import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class BrandRequest {
    @NotNull
    private String brandName;
    private String imageUrl;
}
