package VNGroupBy.com.vn.Mapper.MapperImpl;

import VNGroupBy.com.vn.DTO.response.CartItemRes;
import VNGroupBy.com.vn.Entity.CartItem;
import VNGroupBy.com.vn.Mapper.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component

public class CartItemMappper implements Mapper<CartItem, CartItemRes> {

    @Override
    public CartItem toEntity(CartItemRes dto) {
        return null;
    }

    @Override
    public CartItemRes toDTO(CartItem entity) {
        CartItemRes cartItemRes = new CartItemRes();
        cartItemRes.setId(entity.getId());
        cartItemRes.setCurrentPrice(entity.getCurrentPrice());
        cartItemRes.setQuantity(entity.getQuantity());
        cartItemRes.setBuyType(entity.getBuyType().getName());
        if (entity.getBuyType().getName().equals("product")) {
            cartItemRes.setProductId(entity.getProduct().getId());
            cartItemRes.setProductName(entity.getProduct().getName());
            cartItemRes.setProductBrand(entity.getProduct().getBrand());
        }
         else {
             var campaign = entity.getCampaign();
             cartItemRes.setCampaignId(campaign.getId());
             cartItemRes.setProductId(campaign.getProduct().getId());
             cartItemRes.setProductName(campaign.getProduct().getName());
             cartItemRes.setProductBrand(campaign.getProduct().getBrand());
        }


        return cartItemRes;
    }

    @Override
    public List<CartItemRes> toDTOList(List<CartItem> entityList) {
      return entityList.stream().map(entity->toDTO(entity)).collect(Collectors.toList());

    }

    @Override
    public List<CartItem> toEntityList(List<CartItemRes> dtoList) {
        return null;
    }
}
