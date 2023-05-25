package VNGroupBy.com.vn.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class TestController {
    @GetMapping("test")
    public String test(){
        return "HelloWorld";
    }
}
