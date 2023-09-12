package VNGroupBy.com.vn.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderReq {
    public long cartItemId;
    public  String paymentName;
    public String shipName;
}
