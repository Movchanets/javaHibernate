
package models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tbl_Filters")
@IdClass(FilterPK.class)
public class Filters {
    @Id
    @ManyToOne
    @JoinColumn(name = "FilterName_id", nullable = false)
    private FilterName name;
    @Id
    @ManyToOne
    @JoinColumn(name = "FilterValue_id", nullable = false)
    private FilterValue value;
    @Id
    @ManyToOne
    @JoinColumn(name = "Product_id", nullable = false)
    private Product product;
}