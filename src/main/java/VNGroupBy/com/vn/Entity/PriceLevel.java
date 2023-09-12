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
@Table(name = "price_level")
public class PriceLevel extends BaseEntity {

    private int quantity;
    private int price;
}
