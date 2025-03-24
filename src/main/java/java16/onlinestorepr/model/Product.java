package java16.onlinestorepr.model;

import jakarta.persistence.*;
import java16.onlinestorepr.emum.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    private Category category;
    @ElementCollection
    @Column(name = "images")
    private List<String> images;

    private String madel;

    @Column(name = "characteristic")
    private String characteristic;

    @Column(name = "is_favorite")
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
