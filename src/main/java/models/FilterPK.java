
package models;

        import lombok.Data;

        import java.io.Serializable;

@Data
public class FilterPK implements Serializable {
    private FilterName name;
    private FilterValue value;
    private Product product;
}