
package models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "tbl_FilterValues")
public class FilterValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 500, nullable = false)
    private String name;
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date dateCreated;
    private boolean isDeleted;
    @OneToMany(mappedBy = "value")
    private List<Filters> filters;
    @OneToMany(mappedBy = "value")
    private List<FilterNameGroup> filterNameGroups;
}