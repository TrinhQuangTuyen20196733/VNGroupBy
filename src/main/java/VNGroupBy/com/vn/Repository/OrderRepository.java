package VNGroupBy.com.vn.Repository;


import VNGroupBy.com.vn.DTO.OrderDTO;
import VNGroupBy.com.vn.DTO.ShopOrderDTO;
import VNGroupBy.com.vn.DTO.UserOrderDTO;
import VNGroupBy.com.vn.DTO.response.UserOrderRes;
import VNGroupBy.com.vn.Entity.Order;
import VNGroupBy.com.vn.Security.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {
    @Query(value = "select order_goods.id as orderID,  product.name as productName,shop.name as shopName, payment_type.name as paymentType,item_cart.quantity as quantity, item_cart.current_price as currentPrice,  order_goods.status as status  from order_goods "+
           " inner join item_cart on order_goods.cart_item_id=item_cart.id " +
            " inner join product on item_cart.product_id=product.id "+
            "inner join payment_type on order_goods.payment_type_id=payment_type.id "+
            "inner join shopping_cart on item_cart.shopping_cart_id= shopping_cart.id " +
            "inner join shop on product.shop_id = shop.id " +
            "inner join user on shopping_cart.user_id = user.id and  user.id= ?1 where item_cart.buy_type_id=1 " +
            "union " +
            "select order_goods.id as orderID,  product.name as productName,shop.name as shopName, payment_type.name as paymentType,item_cart.quantity as quantity, item_cart.current_price as currentPrice,  order_goods.status as status  from order_goods "+
            "inner join item_cart on order_goods.cart_item_id=item_cart.id "+
            "inner join campaign on item_cart.campaign_id=campaign.id "+
            "inner join product on campaign.product_id = product.id "+
            "inner join payment_type on order_goods.payment_type_id=payment_type.id "+
            "inner join shopping_cart on item_cart.shopping_cart_id= shopping_cart.id " +
            "inner join shop on product.shop_id = shop.id "+
            "inner join user on shopping_cart.user_id = user.id and  user.id= ?1 where item_cart.buy_type_id=2 "
            , nativeQuery = true)
    Optional<List<UserOrderDTO>> getAllOfUser(Long userId);
    @Query(value = "select order_goods.id as orderID,  product.name as productName, payment_type.name as paymentType,item_cart.quantity as quantity, item_cart.current_price as currentPrice, user.name as customerName, user.address as customerAddress, order_goods.status as status  from order_goods " +
            "           inner join item_cart on order_goods.cart_item_id=item_cart.id " +
            "            inner join product on item_cart.product_id=product.id " +
            "            inner join payment_type on order_goods.payment_type_id=payment_type.id " +
            "            inner join shop on product.shop_id = shop.id and shop.user_id = ?1 " +
            "            inner join shopping_cart on item_cart.shopping_cart_id= shopping_cart.id " +
            "            inner join user on shopping_cart.user_id = user.id " +
            "            where item_cart.buy_type_id=1 " +
            "            union \n" +
            "            select order_goods.id as orderID,  product.name as productName, payment_type.name as paymentType,item_cart.quantity as quantity, item_cart.current_price as currentPrice, user.name as customerName, user.address as customerAddress,  order_goods.status as status  from order_goods " +
            "            inner join item_cart on order_goods.cart_item_id=item_cart.id " +
            "            inner join campaign on item_cart.campaign_id=campaign.id " +
            "            inner join product on campaign.product_id = product.id " +
            "            inner join payment_type on order_goods.payment_type_id=payment_type.id " +
            "            inner join shop on product.shop_id = shop.id  and shop.user_id =?1 " +
            "               inner join shopping_cart on item_cart.shopping_cart_id= shopping_cart.id " +
            "              inner join user on shopping_cart.user_id = user.id " +
            "            where item_cart.buy_type_id=2 "
            , nativeQuery = true)
    Optional<List<ShopOrderDTO>> getAllOfShop(Long userId);
    @Query(value = "select order_goods.id as orderID,  product.name as productName,shop.name as shopName, shop.address as shopAddress, item_cart.quantity as quantity, item_cart.current_price as currentPrice, user.name as customerName, user.address as customerAddress, order_goods.status as status  from order_goods " +
            "           inner join item_cart on order_goods.cart_item_id=item_cart.id " +
            "            inner join product on item_cart.product_id=product.id " +
            "            inner join payment_type on order_goods.payment_type_id=payment_type.id " +
            "            inner join shop on product.shop_id = shop.id " +
            "            inner join shopping_cart on item_cart.shopping_cart_id= shopping_cart.id " +
            "            inner join user on shopping_cart.user_id = user.id " +
            "            where item_cart.buy_type_id=1 " +
            "            union \n" +
            "            select order_goods.id as orderID,  product.name as productName,shop.name as shopName, shop.address as shopAddress,item_cart.quantity as quantity, item_cart.current_price as currentPrice, user.name as customerName, user.address as customerAddress,  order_goods.status as status  from order_goods " +
            "            inner join item_cart on order_goods.cart_item_id=item_cart.id " +
            "            inner join campaign on item_cart.campaign_id=campaign.id " +
            "            inner join product on campaign.product_id = product.id " +
            "            inner join payment_type on order_goods.payment_type_id=payment_type.id " +
            "            inner join shop on product.shop_id = shop.id " +
            "               inner join shopping_cart on item_cart.shopping_cart_id= shopping_cart.id " +
            "              inner join user on shopping_cart.user_id = user.id " +
            "            where item_cart.buy_type_id=2  "
            , nativeQuery = true)
    Optional<List<OrderDTO>> getAll();
}
