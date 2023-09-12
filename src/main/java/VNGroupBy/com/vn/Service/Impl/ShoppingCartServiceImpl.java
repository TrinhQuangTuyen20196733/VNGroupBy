package VNGroupBy.com.vn.Service.Impl;


import VNGroupBy.com.vn.DTO.request.ApiParameter;
import VNGroupBy.com.vn.DTO.response.MessagesResponse;
import VNGroupBy.com.vn.Repository.ShoppingCartRepository;
import VNGroupBy.com.vn.Security.UserDetails;
import VNGroupBy.com.vn.Service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
@Autowired
    ShoppingCartRepository shoppingCartRepository;
    @Override
    public int getCount() {
        var authentication =  SecurityContextHolder.getContext().getAuthentication();
        var user = (UserDetails)authentication.getPrincipal();
        var userId = user.getId();

        var shoppingcart = shoppingCartRepository.findByUserId(userId).get();

        return shoppingcart.getCount();
    }




}
