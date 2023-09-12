package VNGroupBy.com.vn.Service.Impl;

import VNGroupBy.com.vn.DTO.request.ShippingAddressReq;
import VNGroupBy.com.vn.DTO.response.MessagesResponse;
import VNGroupBy.com.vn.Mapper.MapperImpl.ShippingAddressMapper;
import VNGroupBy.com.vn.Repository.ShippingAddressRepository;
import VNGroupBy.com.vn.Repository.UserRepository;
import VNGroupBy.com.vn.Security.UserDetails;
import VNGroupBy.com.vn.Service.ShippingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ShippingAddressServiceImpl implements ShippingAddressService {
    final ShippingAddressRepository shippingAddressRepository;
    final ShippingAddressMapper shippingAddressMapper;
    final UserRepository userRepository;

    public ShippingAddressServiceImpl(ShippingAddressRepository shippingAddressRepository, ShippingAddressMapper shippingAddressMapper, UserRepository userRepository) {
        this.shippingAddressRepository = shippingAddressRepository;
        this.shippingAddressMapper = shippingAddressMapper;
        this.userRepository = userRepository;
    }

    @Override
    public MessagesResponse save(ShippingAddressReq shippingAddressReq) {
        MessagesResponse ms = new MessagesResponse();
        var authentication =  SecurityContextHolder.getContext().getAuthentication();
        var user = (UserDetails)authentication.getPrincipal();
        var userId = user.getId();
        var userEntity = userRepository.findById(userId).get();
        var shippingAdress = shippingAddressMapper.toEntity(shippingAddressReq);
        shippingAdress.setIsDefault(true);
        shippingAdress.setUser(userEntity);
        try {
            shippingAddressRepository.save(shippingAdress);
        } catch (Exception e) {
            ms.code = 500;
            ms.message = "Thực hiện không thành công, vui lòng thử lại";
        }
        return ms;
    }

    @Override
    public MessagesResponse getMe() {
        MessagesResponse ms = new MessagesResponse();
        try {
            var authentication =  SecurityContextHolder.getContext().getAuthentication();
            var user = (UserDetails)authentication.getPrincipal();
            var userId = user.getId();
            var shippingAdress = shippingAddressRepository.findByUserId(userId);
            if (shippingAdress.isPresent()) {
                ms.data=shippingAdress;
            } else {
                ms.code=500;
                ms.message="Lỗi khi lấy ra địa chỉ mặc định của bạn";
            }
        }
        catch (Exception e) {
            ms.code=500;
            ms.message="Lỗi khi lấy ra địa chỉ mặc định của bạn";
        }
  return  ms;


    }
}
