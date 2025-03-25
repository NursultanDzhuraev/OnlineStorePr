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

    private Long id;


    private String brandName;


    private String image;

    @OneToMany(mappedBy = "brand")
    private List<Product> products;
}
