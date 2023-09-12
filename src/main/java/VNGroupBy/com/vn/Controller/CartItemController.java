package VNGroupBy.com.vn.Controller;

import VNGroupBy.com.vn.DTO.request.CartItemReq;
import VNGroupBy.com.vn.DTO.response.MessagesResponse;
import VNGroupBy.com.vn.Service.CartItemService;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart_item")
public class CartItemController {
    @Autowired
    CartItemService cartItemService;

    @PostMapping()
    public MessagesResponse addToCart(@RequestBody CartItemReq cartItemReq) {
        return cartItemService.add(cartItemReq);


    }
    @GetMapping("/GetMe")
    public  MessagesResponse getMe() {
        return cartItemService.getMe();
    }

    @DeleteMapping("/{id}")
    public MessagesResponse delete(@PathVariable @Positive Long id) {
        return  cartItemService.delete(id);
    }


}
