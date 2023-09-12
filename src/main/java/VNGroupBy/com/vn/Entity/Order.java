package VNGroupBy.com.vn.Entity;

import VNGroupBy.com.vn.Utils.Constant.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_goods")
public class Order extends BaseEntity {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_item_id")
    private CartItem cartItem;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "payment_type_id")
    private PaymentType paymentType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ship_fee_id")
    private ShipFee shipFee;

    private int Status = OrderStatus.ApproveWatting;

}
