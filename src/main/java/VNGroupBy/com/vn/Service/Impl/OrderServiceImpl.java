package VNGroupBy.com.vn.Service.Impl;

import VNGroupBy.com.vn.DTO.request.OrderReq;
import VNGroupBy.com.vn.DTO.response.MessagesResponse;
import VNGroupBy.com.vn.DTO.response.OrderRes;
import VNGroupBy.com.vn.DTO.response.ShopOrderRes;
import VNGroupBy.com.vn.DTO.response.UserOrderRes;
import VNGroupBy.com.vn.Entity.Order;
import VNGroupBy.com.vn.Entity.Product;
import VNGroupBy.com.vn.Repository.*;
import VNGroupBy.com.vn.Security.UserDetails;
import VNGroupBy.com.vn.Service.OrderService;
import VNGroupBy.com.vn.Service.WalletService;
import VNGroupBy.com.vn.Utils.Constant.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;


@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ShipFeeRepository shipFeeRepository;
    @Autowired
    PaymentTypeRepository paymentTypeRepository;
    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    WalletService walletService;
    @Override
    public void Save(OrderReq orderReqs) {
        Order order = new Order();
        var ship = shipFeeRepository.findByName(orderReqs.shipName).get();
        var payment = paymentTypeRepository.findByName(orderReqs.paymentName).get();
        var cartItem = cartItemRepository.findById(orderReqs.cartItemId).get();
        order.setCartItem(cartItem);
        order.setPaymentType(payment);
        order.setShipFee(ship);
        orderRepository.save(order);
        if (order.getPaymentType().getName().equals("wallet")) {
            walletService.Withdraw(cartItem.getCurrentPrice()*cartItem.getQuantity());
        } else  {
          if (cartItem.getCampaign()!=null) {
              int deposit = cartItem.getCampaign().getDeposit();
              walletService.Withdraw(deposit);
              walletService.PlusToUser(deposit,cartItem.getCampaign().getProduct().getShop().getUser().getId());
          }
        }

    }

    @Override
    public MessagesResponse GetAllOfUser() {
        MessagesResponse ms = new MessagesResponse();
        var authentication =  SecurityContextHolder.getContext().getAuthentication();
        var user = (UserDetails)authentication.getPrincipal();
        var userId = user.getId();
        try {
          var userOder = orderRepository.getAllOfUser(userId).get();
          var result = userOder.stream().map(order->
                  new UserOrderRes(order.getOrderId(), order.getProductName(), order.getShopName(),order.getPaymentType(),order.getQuantity(),order.getCurrentPrice(),order.getStatus()))
                  .collect(Collectors.toList());
          ms.data= result;
        }
        catch (Exception e) {
            ms.code=404;
            ms.message="Không tìm thấy đơn hàng!";
        }
        return ms;
    }

    @Override
    public MessagesResponse cancel(Long orderId) {
        MessagesResponse ms = new MessagesResponse();
        try {
            var orderOptional = orderRepository.findById(orderId);
            if (orderOptional.isPresent()) {
                var order = orderOptional.get();
                if (order.getStatus()< OrderStatus.Shipping) {

                    orderRepository.delete(order);
                    var cartItem = order.getCartItem();
                    var shoppingCart = cartItem.getShoppingCart();
                    cartItemRepository.delete(cartItem);
                    shoppingCart.setCount(shoppingCart.getCount()-1);
                    if (order.getPaymentType().getName().equals("wallet")) {

                        walletService.ToUp(order.getCartItem().getCurrentPrice()*order.getCartItem().getQuantity());
                    }

                }
            }
            else {
                ms.code = 500;
                ms.message = " Hủy đơn hàng không thành công!";
            }
        }
        catch (Exception e) {
            ms.code = 500;
            ms.message = " Hủy đơn hàng không thành công!";
        }

        return ms;
    }

    @Override
    public MessagesResponse GetAllOfShop() {
        MessagesResponse ms = new MessagesResponse();
        var authentication =  SecurityContextHolder.getContext().getAuthentication();
        var user = (UserDetails)authentication.getPrincipal();
        var userId = user.getId();
        try {
            var shopOder = orderRepository.getAllOfShop(userId).get();
            var result = shopOder.stream().map(order->
                            new ShopOrderRes(order.getOrderId(), order.getProductName(), order.getPaymentType(),order.getQuantity(),order.getCurrentPrice(),order.getStatus(),order.getCustomerName(),order.getCustomerAddress()))
                    .collect(Collectors.toList());
            ms.data= result;
        }
        catch (Exception e) {
            ms.code=404;
            ms.message="Không tìm thấy đơn hàng!";
        }
        return ms;
    }

    @Override
    public MessagesResponse approve(Long orderId) {
        MessagesResponse ms = new MessagesResponse();
        try {
            var orderOptional = orderRepository.findById(orderId);
            if (orderOptional.isPresent()) {
                var order = orderOptional.get();
                if (order.getStatus()==OrderStatus.ApproveWatting)
                order.setStatus(OrderStatus.GoodsPepared);
                orderRepository.save(order);
            }
            else {
                ms.code = 500;
                ms.message = " Không thể xác thực đơn hàng!";
            }
        }
        catch (Exception e) {
            ms.code = 500;
            ms.message = " Không thể xác thực đơn hàng!";
        }

        return ms;
    }

    @Override
    public MessagesResponse findShipper(Long orderId) {
        MessagesResponse ms = new MessagesResponse();
        try {
            var orderOptional = orderRepository.findById(orderId);
            if (orderOptional.isPresent() ) {
                var order = orderOptional.get();
                if (order.getStatus()==OrderStatus.GoodsPepared) {
                    order.setStatus(OrderStatus.ShipperConfirmWatting);
                    orderRepository.save(order);
                }

            }
            else {
                ms.code = 500;
                ms.message = " Không thể xác thực đơn hàng!";
            }
        }
        catch (Exception e) {
            ms.code = 500;
            ms.message = " Không thể xác thực đơn hàng!";
        }

        return ms;
    }

    @Override
    public MessagesResponse GetAllOfShipper() {
        return null;
    }

    @Override
    public MessagesResponse GetAll() {
        MessagesResponse ms = new MessagesResponse();
        var authentication =  SecurityContextHolder.getContext().getAuthentication();
        var user = (UserDetails)authentication.getPrincipal();
        var userId = user.getId();
        try {
            var orders = orderRepository.getAll().get();
            var result = orders.stream().map(order->
                            new OrderRes(order.getOrderId(), order.getProductName(), order.getShopName(), order.getShopAddress(), order.getQuantity(),order.getCurrentPrice(),order.getCustomerName(),order.getCustomerAddress(),order.getStatus()))
                    .collect(Collectors.toList());
            ms.data= result;
        }
        catch (Exception e) {
            ms.code=404;
            ms.message="Không tìm thấy đơn hàng!";
        }
        return ms;
    }

    @Override
    public MessagesResponse pickOrder(Long orderId) {
        MessagesResponse ms = new MessagesResponse();
        try {
            var orderOptional = orderRepository.findById(orderId);
            if (orderOptional.isPresent()) {
                var order = orderOptional.get();
                if (order.getStatus()==OrderStatus.ShipperConfirmWatting)
                    order.setStatus(OrderStatus.ShipperPickWatting);
                orderRepository.save(order);
            }
            else {
                ms.code = 500;
                ms.message = " Không thể xác thực đơn hàng!";
            }
        }
        catch (Exception e) {
            ms.code = 500;
            ms.message = " Không thể xác thực đơn hàng!";
        }

        return ms;
    }

    @Override
    public MessagesResponse receiveOrder(Long orderId) {
        MessagesResponse ms = new MessagesResponse();
        try {
            var orderOptional = orderRepository.findById(orderId);
            if (orderOptional.isPresent()) {
                var order = orderOptional.get();
                var authentication =  SecurityContextHolder.getContext().getAuthentication();
                var user = (UserDetails)authentication.getPrincipal();
                var userId = user.getId();
                walletService.Withdraw(order.getCartItem().getQuantity()*order.getCartItem().getCurrentPrice());
                Product product = new Product();
                if (order.getCartItem().getCampaign()!=null) {
                    product = order.getCartItem().getCampaign().getProduct();
                } else {
                    product = order.getCartItem().getProduct();
                }
                walletService.PlusToUser(order.getCartItem().getQuantity()*order.getCartItem().getCurrentPrice(),product.getShop().getUser().getId());
                if (order.getStatus()==OrderStatus.ShipperPickWatting)
                    order.setStatus(OrderStatus.Shipping);
                orderRepository.save(order);
            }
            else {
                ms.code = 500;
                ms.message = " Không thể xác thực đơn hàng!";
            }
        }
        catch (Exception e) {
            ms.code = 500;
            ms.message = " Không thể xác thực đơn hàng!";
        }

        return ms;
    }

    @Override
    public MessagesResponse DoneOrder(Long orderId) {
        MessagesResponse ms = new MessagesResponse();
        try {
            var orderOptional = orderRepository.findById(orderId);
            if (orderOptional.isPresent()) {
                var order = orderOptional.get();
                var authentication =  SecurityContextHolder.getContext().getAuthentication();
                var user = (UserDetails)authentication.getPrincipal();
                var userId = user.getId();
                if (order.getPaymentType().getName().equals("wallet")) {
                    walletService.PlusToUser(order.getCartItem().getQuantity()*order.getCartItem().getCurrentPrice()+order.getShipFee().getFee(),userId);
                }
                if (order.getStatus()==OrderStatus.Shipping)
                    order.setStatus(OrderStatus.Done);
                orderRepository.save(order);
            }
            else {
                ms.code = 500;
                ms.message = " Không thể xác thực đơn hàng!";
            }
        }
        catch (Exception e) {
            ms.code = 500;
            ms.message = " Không thể xác thực đơn hàng!";
        }

        return ms;
    }

    @Override
    public MessagesResponse ShipperCancel(Long orderId) {
        MessagesResponse ms = new MessagesResponse();
        try {
            var orderOptional = orderRepository.findById(orderId);
            if (orderOptional.isPresent()) {
                var order = orderOptional.get();
                if (order.getStatus()==OrderStatus.Shipping)
                    order.setStatus(OrderStatus.Reject);
                orderRepository.save(order);
            }
            else {
                ms.code = 500;
                ms.message = " Không thể xác thực đơn hàng!";
            }
        }
        catch (Exception e) {
            ms.code = 500;
            ms.message = " Không thể xác thực đơn hàng!";
        }

        return ms;
    }

    @Override
    public MessagesResponse cancelShipperWatting(Long orderId) {
        MessagesResponse ms = new MessagesResponse();
        try {
            var orderOptional = orderRepository.findById(orderId);
            if (orderOptional.isPresent()) {
                var order = orderOptional.get();
                if (order.getStatus()==OrderStatus.ShipperConfirmWatting)
                    order.setStatus(OrderStatus.GoodsPepared);
                orderRepository.save(order);
            }
            else {
                ms.code = 500;
                ms.message = " Không thể xác thực đơn hàng!";
            }
        }
        catch (Exception e) {
            ms.code = 500;
            ms.message = " Không thể xác thực đơn hàng!";
        }

        return ms;
    }

    @Override
    public MessagesResponse SendBackOrder(Long orderId) {
        MessagesResponse ms = new MessagesResponse();
        try {
            var orderOptional = orderRepository.findById(orderId);
            if (orderOptional.isPresent()) {
                var order = orderOptional.get();
                if (order.getStatus()==OrderStatus.Reject)
                    order.setStatus(OrderStatus.SenBack);
                orderRepository.save(order);
            }
            else {
                ms.code = 500;
                ms.message = " Không thể xác thực đơn hàng!";
            }
        }
        catch (Exception e) {
            ms.code = 500;
            ms.message = " Không thể xác thực đơn hàng!";
        }

        return ms;
    }

    @Override
    public MessagesResponse ApproveSendBack(Long orderId) {
        MessagesResponse ms = new MessagesResponse();
        try {
            var orderOptional = orderRepository.findById(orderId);
            if (orderOptional.isPresent()) {
                var order = orderOptional.get();


                if (order.getStatus()==OrderStatus.SenBack)
                    order.setStatus(OrderStatus.Done);
                orderRepository.save(order);
            }
            else {
                ms.code = 500;
                ms.message = " Không thể xác thực đơn hàng!";
            }
        }
        catch (Exception e) {
            ms.code = 500;
            ms.message = " Không thể xác thực đơn hàng!";
        }

        return ms;
    }
}
