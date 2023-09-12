package VNGroupBy.com.vn.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ship_fee")
public class ShipFee  extends  BaseEntity{
    private String name;
    private String code;
    private int fee;
}
