package VNGroupBy.com.vn.Controller;

import VNGroupBy.com.vn.DTO.response.MessagesResponse;
import VNGroupBy.com.vn.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorys")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public MessagesResponse getAll() {
        MessagesResponse ms = new MessagesResponse();
        try {
            ms.data = categoryService.getAll();

        }
        catch (Exception e) {
            ms.code = 500;
            ms.setMessage("Intenal Server Error!");
        }
        return  ms;
    }
}
