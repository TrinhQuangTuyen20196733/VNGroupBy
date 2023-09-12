package VNGroupBy.com.vn.Controller;

import VNGroupBy.com.vn.DTO.response.MessagesResponse;
import VNGroupBy.com.vn.Service.ShipFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ship_fee")
public class ShipFeeController {
    @Autowired
    ShipFeeService shipFeeService;

    @GetMapping
    public MessagesResponse getAll() {
        return shipFeeService.getAll();
    }
}
