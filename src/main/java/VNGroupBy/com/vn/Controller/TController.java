package VNGroupBy.com.vn.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TController {
@GetMapping("/test")
    public String oke(){
    return  "OKee";
};

}