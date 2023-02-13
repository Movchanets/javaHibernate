package models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="tbl_products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 500, nullable = false)
    private String name;
    private double price;
    @Column(length = 4000)
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date dateCreated;
    private boolean isDeleted;
    @ManyToOne
    @JoinColumn(name="category_id", nullable = false)
    private Category category;
    @OneToMany(mappedBy = "product")
    private List<Filters> filters;
    @OneToMany(mappedBy = "product")
    private List<Basket> baskets;
    public Product() {
        baskets= new ArrayList<>();
        filters= new ArrayList<>();
    }
    public Product(String name, Date dateCreated, boolean isDeleted) {
        super();
        this.name = name;
        this.dateCreated = dateCreated;
        this.isDeleted = isDeleted;

    }
}