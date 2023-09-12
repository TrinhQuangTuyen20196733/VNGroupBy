package VNGroupBy.com.vn.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopOrderRes {
    public  long orderId;
    public  String productName;
    public String paymentType;
    public  int quantity;
    public int currentPrice;
    public  int status;
    public String customerName;
    public  String customerAddress;
}
