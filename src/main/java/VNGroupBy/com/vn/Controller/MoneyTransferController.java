package VNGroupBy.com.vn.Controller;

import VNGroupBy.com.vn.DTO.request.ApiParameter;
import VNGroupBy.com.vn.DTO.response.MessagesResponse;
import VNGroupBy.com.vn.Mapper.MapperImpl.MoneyTransferMapper;
import VNGroupBy.com.vn.Service.MoneyTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/moneyTransfer")
public class MoneyTransferController {
    @Autowired
    private MoneyTransferService moneyTransferService;

    @PostMapping("/getMe")
    public MessagesResponse GetMe(@RequestBody ApiParameter apiParameter) {
        MessagesResponse ms = new MessagesResponse();
        var lst = moneyTransferService.getMe(apiParameter);
        MoneyTransferMapper mapper = new MoneyTransferMapper();
        ms.data = mapper.toDTOList(lst);
        return ms;

    }

}
