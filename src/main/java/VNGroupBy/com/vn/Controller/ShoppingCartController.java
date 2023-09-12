package VNGroupBy.com.vn.Controller;

import VNGroupBy.com.vn.DTO.request.ApiParameter;
import VNGroupBy.com.vn.DTO.response.MessagesResponse;
import VNGroupBy.com.vn.Service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping_cart")
public class ShoppingCartController {
    @Autowired
    ShoppingCartService shoppingCartService;
    @GetMapping("/count")
    public  int getCount() {
        return  shoppingCartService.getCount();
    }

}
