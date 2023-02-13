
package models;

        import lombok.Data;

        import javax.persistence.*;

@Data
@Entity
@Table(name="tbl_FilterNameGroups")
@IdClass(FilterNameGroupPK.class)
public class FilterNameGroup {
    @Id
    @ManyToOne
    @JoinColumn(name="FilterName_id", nullable = false)
    private FilterName name;
    @Id
    @ManyToOne
    @JoinColumn(name="FilterValue_id", nullable = false)
    private FilterValue value;
}