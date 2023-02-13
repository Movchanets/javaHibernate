package models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "tbl_categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 200, nullable = false)
    private String name;
    @Column(length = 200)
    private String image;
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date dateCreated;
    private boolean isDeleted;
    @OneToMany(mappedBy = "category")
    private List<Product> products;
    public Category() {
        products= new ArrayList<>();
    }

    public Category(String name, String image, Date dateCreated, boolean isDeleted) {
        super();
        this.name = name;
        this.image = image;
        this.dateCreated = dateCreated;
        this.isDeleted = isDeleted;
    }
}