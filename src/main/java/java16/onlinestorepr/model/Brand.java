package java16.onlinestorepr.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "brand")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "brand_name")
    private String brandName;

    @Column(name = "image")
    private String image;

    @OneToMany(mappedBy = "brand")
    private List<Product> products;
}
