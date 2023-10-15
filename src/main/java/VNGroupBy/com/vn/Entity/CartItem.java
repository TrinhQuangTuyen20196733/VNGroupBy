package VNGroupBy.com.vn.Entity;

import VNGroupBy.com.vn.Utils.Constant.CartStatus;
import com.sun.jdi.ByteType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "item_cart")
public class CartItem extends BaseEntity {
    @ManyToOne(fetch = FetchType.EAGER, cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST,
    })
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCart shoppingCart;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;

    private int quantity;
    @Column(name = "current_price")
    private int currentPrice;

    private int status = CartStatus.Added;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "buy_type_id")
    private BuyType buyType;

}
