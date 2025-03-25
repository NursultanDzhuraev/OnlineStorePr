package java16.onlinestorepr.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaginationResponse<T> {
    private int pageNumber;
    private int pageSize;
    private long totalPages;
    private long totalElements;
    private List<T> content;

}