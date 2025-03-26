package java16.onlinestorepr.repo.jdbc.impl;

import java16.onlinestorepr.dto.response.ProductResponse;
import java16.onlinestorepr.emum.Category;
import java16.onlinestorepr.exceptions.NotFoundException;
import java16.onlinestorepr.repo.jdbc.FavoriteJdbc;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteJdbcImpl implements FavoriteJdbc {

    private final JdbcClient jdbcClient;

    @Override
    public String toggleFavorite(Long userId, Long productId) {
      try {
          String checkSql = "select count(*) from favorites where user_id = ? and product_id = ?";

          Long count = jdbcClient.sql(checkSql)
                  .params(userId, productId)
                  .query(Long.class)
                  .single();

          if (count > 0) {
              String deleteSql = "delete from favorites where user_id = ? and product_id = ?";
              jdbcClient.sql(deleteSql)
                      .params(userId, productId)
                      .update();
              
              return "removed";
          } else {
              String insertSql = "insert into favorites (user_id, product_id) values (?, ?)";
              jdbcClient.sql(insertSql)
                      .params(userId, productId)
                      .update();
              return "added";
          }
      }catch (Exception e){
          throw new NotFoundException(e.getMessage());
      }

    }

    public List<ProductResponse> getFavoriteProducts(Long userId) {
        String sql = """
                select p.id, p.name, p.price, p.category, p.characteristic, p.madel, p.images
                from product p
                join favorites f on p.id = f.product_id
                where f.user_id = ?
                """;

        return jdbcClient.sql(sql)
                .param(userId)
                .query((rs, rowNum) -> ProductResponse.builder()
                        .id(rs.getLong("id"))
                        .name(rs.getString("name"))
                        .price(rs.getDouble("price"))
                        .category(Category.valueOf(rs.getString("category")))
                        .characteristic(rs.getString("characteristic"))
                        .madel(rs.getString("madel"))
                        .images(Arrays.asList(rs.getString("images").split(",")))
                        .build()
                ).list();
    }
}
