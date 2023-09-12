package VNGroupBy.com.vn.DTO.request;

import VNGroupBy.com.vn.Entity.PriceLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampaignReq {

    public long id;
    public  String productName;
    public  int soldQuantity;
    public  String productBrand;
    public  String name;
    public Date startTime;
    public  Date endTime;
    public  int deposit;
    public  int inStock;
    public List<PriceLevel> levels;
    public  int currentPrice;
    public  String productImage;
    public  String productCategory;


}
