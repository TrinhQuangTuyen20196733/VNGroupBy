package VNGroupBy.com.vn.DTO.request;

import com.sun.jdi.ByteType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemReq {
    public long product_id;
    public int quantity;
    public int currentPrice;
    public  long campaign_id;
    public String buyType;
}
