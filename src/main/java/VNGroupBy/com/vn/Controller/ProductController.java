package VNGroupBy.com.vn.Controller;

import VNGroupBy.com.vn.DTO.request.ApiParameter;
import VNGroupBy.com.vn.DTO.request.ProductReq;
import VNGroupBy.com.vn.DTO.response.MessagesResponse;
import VNGroupBy.com.vn.Service.ProductService;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;
    @PostMapping
    public MessagesResponse create(@RequestBody ProductReq productReq) {
     return  productService.save(productReq);
    }
    @PostMapping("/GetItems")
    public  MessagesResponse GetItems(@RequestBody ApiParameter apiParameter){
        return  productService.getItems(apiParameter);
    }
    @GetMapping("/{id}")
    public  MessagesResponse GetById(@PathVariable @Positive long id) {
        return  productService.getById(id);
    }
    @DeleteMapping("/{id}")
    public  MessagesResponse Delete(@PathVariable @Positive long id) {
        return  productService.deleteById(id);
    }
    
}
