
package models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tbl_productImages")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 500, nullable = false)
    private String name;
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date dateCreated;
    private boolean isDeleted;
    @ManyToOne
    @JoinColumn(name = "tbl_products", nullable = false)
    private Product product;
}