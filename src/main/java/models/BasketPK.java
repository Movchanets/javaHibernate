
package models;

        import lombok.Data;

        import java.io.Serializable;

@Data
public class BasketPK implements Serializable {
    private User user;
    private Product product;
}