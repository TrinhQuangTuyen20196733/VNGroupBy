package VNGroupBy.com.vn.DTO;

public interface ShopOrderDTO {
    public  long getOrderId();
    public  String getProductName();
    public String getPaymentType();
    public  int getQuantity();
    public int getCurrentPrice();
    public  int getStatus();
    public String getCustomerName();
    public  String getCustomerAddress();
}
