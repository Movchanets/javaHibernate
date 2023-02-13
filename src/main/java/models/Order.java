
package models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tbl_Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date dateCreated;
    private boolean isDeleted;
    @ManyToOne
    @JoinColumn(name = "OrderStatus_id", nullable = false)
    private OrderStatus status;
    @ManyToOne
    @JoinColumn(name = "User_id", nullable = false)
    private User user;

}