package VNGroupBy.com.vn.Service.Impl;

import VNGroupBy.com.vn.DTO.request.ApiParameter;
import VNGroupBy.com.vn.DTO.request.ProductReq;
import VNGroupBy.com.vn.DTO.response.MessagesResponse;
import VNGroupBy.com.vn.Mapper.MapperImpl.ProductMapper;
import VNGroupBy.com.vn.Repository.CustomizedRepository.CustomProductRepository;
import VNGroupBy.com.vn.Repository.ProductRepositoty;
import VNGroupBy.com.vn.Repository.ShopRepository;
import VNGroupBy.com.vn.Security.UserDetails;
import VNGroupBy.com.vn.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ShopRepository shopRepository;

    @Autowired
    ProductRepositoty productRepositoty;
    @Autowired
    CustomProductRepository customProductRepository;

    @Override
    public MessagesResponse save(ProductReq productReq) {
        MessagesResponse ms = new MessagesResponse();
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = (UserDetails) authentication.getPrincipal();
        var userId = user.getId();
        var shopOptional = shopRepository.findByUserId(userId);
        if (shopOptional.isPresent()) {
            var product = productMapper.toEntity(productReq);
            product.setShop(shopOptional.get());
            productRepositoty.save(product);

        } else {
            ms.code = 500;
            ms.message = " Internal Server Error!";
        }
        return ms;
    }

    @Override
    public MessagesResponse getItems(ApiParameter apiParameter) {
        MessagesResponse ms = new MessagesResponse();

        try {
            var product = customProductRepository.getByFilter(apiParameter);
            ms.data = productMapper.toDTOList(product);
        } catch (Exception e) {
            ms.code = 404;
            ms.message = "Item Not Found!";
        }
        return ms;
    }

    @Override
    public MessagesResponse deleteById(long id) {
        MessagesResponse ms = new MessagesResponse();
        try {
            productRepositoty.deleteById(id);

        } catch (Exception ex) {
            ms.code = 500;
            ms.message = "Lỗi khi thao tác xóa sản phẩm. Vui lòng thử lại!";
        }
        return ms;
    }

    @Override
    public MessagesResponse getById(long id) {
        MessagesResponse ms = new MessagesResponse();
        try {
            var product = productRepositoty.findById(id).get();
            ms.data = productMapper.toDTO(product);
        } catch (Exception ex) {
            ms.code = 404;
            ms.message = "Item Not Found!";
        }
        return  ms;
    }
}
