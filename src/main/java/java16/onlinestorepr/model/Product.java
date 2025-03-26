package java16.onlinestorepr.model;

import jakarta.persistence.*;
import java16.onlinestorepr.emum.Category;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Double price;

    private Category category;
    @ElementCollection
    @Column(name = "images")
    private List<String> images;

    private String madel;


    private String characteristic;

    private Boolean isFavorite;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToMany(mappedBy = "products")
    private List<Basket> baskets;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "product")
    private List<Favorite> favorites;
}
