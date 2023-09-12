package VNGroupBy.com.vn.Service.Impl;

import VNGroupBy.com.vn.DTO.request.CartItemReq;
import VNGroupBy.com.vn.DTO.response.MessagesResponse;
import VNGroupBy.com.vn.Entity.CartItem;
import VNGroupBy.com.vn.Entity.PriceLevel;
import VNGroupBy.com.vn.Entity.ShoppingCart;
import VNGroupBy.com.vn.Mapper.MapperImpl.CartItemMappper;
import VNGroupBy.com.vn.Repository.*;
import VNGroupBy.com.vn.Security.UserDetails;
import VNGroupBy.com.vn.Service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    ShoppingCartRepository shoppingCartRepository;
    @Autowired
    ProductRepositoty productRepositoty;
    @Autowired
    CartItemMappper cartItemMappper;
    @Autowired
    CampaignRepository campaignRepository;
    @Autowired
    BuyTypeRepository buyTypeRepository;
    @Override
    public MessagesResponse add(CartItemReq cartItemReq) {
        MessagesResponse ms = new MessagesResponse();
        var authentication =  SecurityContextHolder.getContext().getAuthentication();
        var user = (UserDetails)authentication.getPrincipal();
        var userId = user.getId();
        var shoppingCart = shoppingCartRepository.findByUserId(userId).get();
        var buyType = buyTypeRepository.findByName(cartItemReq.buyType).get();
        try {
            if (cartItemReq.buyType.equals("product") && cartItemReq.product_id>0) {
                var product = productRepositoty.findById(cartItemReq.product_id).get();
                if (product.getInStock() > cartItemReq.getQuantity()) {
                    CartItem cartItem = new CartItem();
                    cartItem.setShoppingCart(shoppingCart);
                    cartItem.setProduct(product);
                    cartItem.setQuantity(cartItemReq.getQuantity());
                    cartItem.setCurrentPrice(cartItemReq.getCurrentPrice());
                    cartItem.setBuyType(buyType);
                    cartItemRepository.save(cartItem);
                    shoppingCart.setCount(shoppingCart.getCount() + 1);
                    shoppingCartRepository.save(shoppingCart);
                    product.setInStock(product.getInStock() - cartItemReq.getQuantity());
                    productRepositoty.save(product);
                } else {
                    ms.code = 500;
                    ms.message = " Không đủ hàng";
                }
            }
            else {
                var campaign =  campaignRepository.findById(cartItemReq.campaign_id).get();
                if (campaign.getInStock() > cartItemReq.quantity) {
                    CartItem cartItem = new CartItem();
                    cartItem.setShoppingCart(shoppingCart);
                    cartItem.setCampaign(campaign);
                    cartItem.setQuantity(cartItemReq.quantity);
                    var priceLevels = campaign.getLevels();
                    // Sử dụng Stream API để sắp xếp giảm dần theo quantity
                    List<PriceLevel> sortedPriceLevels = priceLevels.stream()
                            .sorted((pl1, pl2) -> pl1.getQuantity() - pl2.getQuantity())
                            .collect(Collectors.toList());
                    PriceLevel firstPrice= sortedPriceLevels.stream()
                            .filter(priceLevel -> priceLevel.getQuantity() < cartItemReq.getQuantity())
                            .findFirst() // Lấy phần tử đầu tiên thỏa điều kiện
                            .orElse(null); // Trả về null nếu không tìm thấy
                    if (firstPrice!=null) {
                        cartItem.setCurrentPrice(firstPrice.getPrice());
                    } else {

                        cartItem.setCurrentPrice(cartItemReq.currentPrice);
                    }
                    cartItem.setBuyType(buyType);
                    cartItemRepository.save(cartItem);
                    shoppingCart.setCount(shoppingCart.getCount()+ 1);
                    shoppingCartRepository.save(shoppingCart);
                    campaign.setInStock(campaign.getInStock()- cartItemReq.getQuantity());
                    campaignRepository.save(campaign);

                }
            }

        }
        catch (Exception e) {
           ms.code=500;
           ms.message="Không thể thêm mới item này vào giỏ hàng";
        }

      ms.data= shoppingCart.getCount();
        return ms;
    }

    @Override
    public MessagesResponse getMe() {
        MessagesResponse ms = new MessagesResponse();
        try {
            var authentication =  SecurityContextHolder.getContext().getAuthentication();
            var user = (UserDetails)authentication.getPrincipal();
            var userId = user.getId();
            var shoppingCart = shoppingCartRepository.findByUserId(userId).get();
            var list = cartItemRepository.findShoppingCartId(shoppingCart.getId()).get();
           ms.data = cartItemMappper.toDTOList(list);

        }
        catch (Exception e) {
            ms.code = 500;
            ms.message=" Internal Server Error";
        }
        return  ms;
    }

    @Override
    public MessagesResponse delete(Long id) {
        MessagesResponse ms = new MessagesResponse();
        try {
            var cartItem = cartItemRepository.findById(id).get();
            ShoppingCart shoppingCart = cartItem.getShoppingCart();
            cartItemRepository.delete(cartItem);
            shoppingCart.setCount(shoppingCart.getCount()-1);
        }
        catch (Exception e) {
            ms.code = 500;
            ms.message=" Internal Server Error";
        }
        return  ms;
    }


}
