package models;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
@Data
@Entity
@Table(name = "Roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 255, nullable = false)
    private String name;
    @OneToMany(mappedBy = "role")
    private List<UserRole> userRoles;
    public Role() {
        userRoles = new ArrayList<>();
    }
}
