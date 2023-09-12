package VNGroupBy.com.vn.Controller;

import VNGroupBy.com.vn.DTO.request.ShippingAddressReq;
import VNGroupBy.com.vn.DTO.response.MessagesResponse;
import VNGroupBy.com.vn.Service.ShippingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shipping_address")
public class ShippingAddressController {
    @Autowired
    ShippingAddressService shippingAddressService;
    @PostMapping
    public MessagesResponse Save(@RequestBody ShippingAddressReq shippingAddressReq) {
  return  shippingAddressService.save(shippingAddressReq);
    }
    @GetMapping("/GetMe")
    public MessagesResponse GetMe() {
        return  shippingAddressService.getMe();
    }
}
