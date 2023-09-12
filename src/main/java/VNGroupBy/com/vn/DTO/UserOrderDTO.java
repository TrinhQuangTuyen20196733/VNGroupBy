package VNGroupBy.com.vn.DTO;

public interface UserOrderDTO {
    public  long getOrderId();
    public  String getProductName();
    public  String getShopName();
    public String getPaymentType();
    public  int getQuantity();
    public int getCurrentPrice();
    public  int getStatus();
}
