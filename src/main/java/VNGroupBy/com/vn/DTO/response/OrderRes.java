package VNGroupBy.com.vn.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRes {
    public  long orderId;
    public  String productName;
    public  String shopName;
    public  String shopAddress;
    public  int quantity;
    public int currentPrice;
    public String customerName;
    public  String customerAddress;
    public  int status;
}
