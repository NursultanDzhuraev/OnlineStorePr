package java16.onlinestorepr.repo.jdbc.impl;

import java16.onlinestorepr.dto.response.*;
import java16.onlinestorepr.emum.Category;
import java16.onlinestorepr.repo.jdbc.ProductJdbc;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductJdbcImpl implements ProductJdbc {

    private final JdbcTemplate jdbcTemplate;
    private final JdbcClient jdbcClient;

    @Override
    public PaginationResponse<ProductResponse> getProductWithCategoryAndPrice(
            String category, String ascOrDesc, int pageNumber, int pageSize) {

        String countSql = "select count(*) from product where upper(category) = upper(?)";
        Long total = jdbcTemplate.queryForObject(countSql, new Object[]{category}, Long.class);

        int offset = (pageNumber - 1) * pageSize;

        String sql = """
                select id, name, price, category, characteristic, madel, images
                from product
                where upper(category) = upper(?)
                order by  price """ + ("ASC".equalsIgnoreCase(ascOrDesc) ? "asc" : "desc") + """
                limit ? offset ?
                """;


        List<ProductResponse> products = jdbcTemplate.query(sql,
                new Object[]{category, pageSize, offset},
                (rs, rowNum) -> ProductResponse.builder()
                        .id(rs.getLong("id"))
                        .name(rs.getString("name"))
                        .price(rs.getDouble("price"))
                        .category(Category.valueOf(rs.getString("category")))
                        .characteristic(rs.getString("characteristic"))
                        .madel(rs.getString("madel"))
                        .images(Arrays.asList(rs.getString("images").split(",")))
                        .build()
        );

        return PaginationResponse.<ProductResponse>builder()
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .totalElements(total)
                .totalPages((int) Math.ceil((double) total / pageSize))
                .content(products)
                .build();

    }

    @Override
    public ProductResponseAndLike getProductByIdCountLike(Long productId) {
        String sql = """
            select p.id, p.name, p.price, p.category, p.characteristic, p.madel, p.images,
                   count(f.product_id) as like_count
            from product p
            left join favorites f ON p.id = f.product_id
            where p.id = ?
            group by  p.id, p.name, p.price, p.category, p.characteristic, p.madel, p.images
            """;

        return jdbcClient.sql(sql)
                .param(productId)
                .query((rs, rowNum) -> ProductResponseAndLike.builder()
                        .productResponse(ProductResponse.builder()
                                .id(rs.getLong("id"))
                                .name(rs.getString("name"))
                                .price(rs.getDouble("price"))
                                .category(Category.valueOf(rs.getString("category")))
                                .characteristic(rs.getString("characteristic"))
                                .madel(rs.getString("madel"))
                                .images(Arrays.asList(rs.getString("images").split(",")))
                                .build())
                        .likeCount(rs.getLong("like_count"))
                        .build()
                ).single();
    }

    @Override
    public ProductResponseAndComment getProductByIdAndComment(Long productId) {
        String productSql = """
            select id, name, price, category, characteristic, madel, images
            from product
            where id = ?
            """;

        ProductResponse productResponse = jdbcClient.sql(productSql)
                .param(productId)
                .query((rs, rowNum) -> ProductResponse.builder()
                        .id(rs.getLong("id"))
                        .name(rs.getString("name"))
                        .price(rs.getDouble("price"))
                        .category(Category.valueOf(rs.getString("category")))
                        .characteristic(rs.getString("characteristic"))
                        .madel(rs.getString("madel"))
                        .images(Arrays.asList(rs.getString("images").split(",")))
                        .build())
                .single();
        String commentSql = """
            select c.id, c.comment, c.created_date, u.first_name, u.last_name
            from comment c
            join users u ON c.user_id = u.id
            where c.product_id = ?
            """;
        List<CommentResponse> comments = jdbcClient.sql(commentSql)
                .param(productId)
                .query((rs, rowNum) -> CommentResponse
                        .builder()
                        .id(rs.getLong("id"))
                        .comment(rs.getString("comment"))
                        .createdAt(rs.getTimestamp("created_date").toLocalDateTime())
                        .username(rs.getString("first_name") + " " + rs.getString("last_name"))
                        .build()
                ).list();
        return ProductResponseAndComment.builder()
                .productResponse(productResponse)
                .comments(comments)
                .build();
    }

}
