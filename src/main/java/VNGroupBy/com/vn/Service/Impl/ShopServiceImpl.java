package VNGroupBy.com.vn.Service.Impl;

import VNGroupBy.com.vn.DTO.response.MessagesResponse;
import VNGroupBy.com.vn.DTO.response.ShopRes;
import VNGroupBy.com.vn.Entity.Shop;
import VNGroupBy.com.vn.Mapper.MapperImpl.ShopMapper;
import VNGroupBy.com.vn.Repository.ShopRepository;
import VNGroupBy.com.vn.Repository.UserRepository;
import VNGroupBy.com.vn.Security.UserDetails;
import VNGroupBy.com.vn.Service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    ShopRepository shopRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ShopMapper shopMapper;
    @Override
    public MessagesResponse save(Shop shop) {
        MessagesResponse ms = new MessagesResponse();
        try {
            var authentication =  SecurityContextHolder.getContext().getAuthentication();
            var user = (UserDetails)authentication.getPrincipal();
            var userId = user.getId();

            var userAccount = userRepository.findById(userId).get();
            shop.setUser(userAccount);
            shopRepository.save(shop);
        }
        catch (Exception e) {
            ms.code=400;
            ms.message="Cập nhật thông tin shop không thành công";
        }
        return  ms;
    }

    @Override
    public MessagesResponse getInfo() {
        MessagesResponse ms = new MessagesResponse();
        var authentication =  SecurityContextHolder.getContext().getAuthentication();
        var user = (UserDetails)authentication.getPrincipal();
        var userId = user.getId();
        var shopOptional = shopRepository.findByUserId(userId);
        if (shopOptional.isPresent()) {
            var shop = shopOptional.get();
           var shopRes = shopMapper.toDTO(shop);
           ms.data= shopRes;

        } else  {
            ms.code=404;
            ms.message="Không tìm thấy thông tin cửa hàng, vui lòng thử lại!";
        }
        return  ms;
    }
}
