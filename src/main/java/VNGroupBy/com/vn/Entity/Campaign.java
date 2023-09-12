package VNGroupBy.com.vn.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "campaign")
public class Campaign extends  BaseEntity {

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    private String name;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    private  int deposit;

    @Column(name = "sold_quantity")
    private  int soldQuantity=0;

    @Column(name = "in_stock")
    private  int inStock;

    @Column(name = "is_active")
    private  boolean isActive;

    @Column(name = "current_price")
    private  int currentPrice;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_id")
    private List<PriceLevel> levels = new ArrayList<>();

}
