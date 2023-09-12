package VNGroupBy.com.vn.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOrderRes {
    public  long orderId;
    public String productName;
    public String shopName;
    public  String paymentType;
    public int quantity;
    public int  currentPrice;
    public int status;



}
