package VNGroupBy.com.vn.Controller;

import VNGroupBy.com.vn.DTO.response.MessagesResponse;
import VNGroupBy.com.vn.Service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallet")
public class WalletController {
    @Autowired
    private WalletService walletService;

    @PostMapping("/toup")
    public  MessagesResponse ToUp(@RequestBody Integer  money) {
        return walletService.ToUp(money);
    }
    @PostMapping("/withdraw")
    public  MessagesResponse Withdraw(@RequestBody  Integer money) {
        return  walletService.Withdraw(money);
    }
    @GetMapping("/balance")
    public  MessagesResponse GetBalance(){
        return  walletService.getBalance();
    }
}
