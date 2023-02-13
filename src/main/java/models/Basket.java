
package models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tbl_Baskets")
@IdClass(BasketPK.class)
public class Basket {
    @Id
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column( nullable = false)
    private int Count;
}