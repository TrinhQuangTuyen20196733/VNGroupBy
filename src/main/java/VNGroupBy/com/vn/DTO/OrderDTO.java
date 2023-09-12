package VNGroupBy.com.vn.DTO;

public interface OrderDTO {
    public  long getOrderId();
    public  String getProductName();
    public  String getShopName();
    public  String getShopAddress();
    public  int getQuantity();
    public int getCurrentPrice();
    public String getCustomerName();
    public  String getCustomerAddress();
    public  int getStatus();
}
