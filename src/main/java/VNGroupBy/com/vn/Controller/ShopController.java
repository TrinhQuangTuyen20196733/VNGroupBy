package VNGroupBy.com.vn.Controller;

import VNGroupBy.com.vn.DTO.response.MessagesResponse;
import VNGroupBy.com.vn.Entity.Shop;
import VNGroupBy.com.vn.Service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shops")
public class ShopController {
    @Autowired
    private ShopService shopService;
    @PostMapping()
    MessagesResponse  createShop(@RequestBody Shop shop){
  return   shopService.save(shop);
    }
    @GetMapping("/getInfo")
    MessagesResponse GetInfo(){
        return  shopService.getInfo();
    }

}
