package java16.onlinestorepr.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PaginationResponse<T> {
    private int pageNumber;
    private int pageSize;
    private long totalPages;
    private long totalElements;
    private List<T> content;

}