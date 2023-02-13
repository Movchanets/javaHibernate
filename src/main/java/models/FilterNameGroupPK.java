
package models;

import lombok.Data;

import java.io.Serializable;

@Data
public class FilterNameGroupPK implements Serializable {
    private FilterName name;
    private FilterValue value;
}