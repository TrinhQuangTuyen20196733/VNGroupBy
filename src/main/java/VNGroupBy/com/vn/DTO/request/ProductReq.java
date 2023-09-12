package VNGroupBy.com.vn.DTO.request;

import VNGroupBy.com.vn.Entity.Category;
import VNGroupBy.com.vn.Entity.Shop;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductReq {
    public  int id;
    public String name;

    public String description;


    public int originPrice;

    public int price;

    public String brand;


    public String category;

    public String origin;

    public int inStock;


    public String imageUrl;
    public int soldQuantity;


}
