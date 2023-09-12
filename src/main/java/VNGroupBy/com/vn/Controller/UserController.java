package VNGroupBy.com.vn.Controller;

import VNGroupBy.com.vn.DTO.request.RegisterReq;
import VNGroupBy.com.vn.DTO.request.UpdatePassReq;
import VNGroupBy.com.vn.DTO.response.MessagesResponse;
import VNGroupBy.com.vn.DTO.response.UserRes;
import VNGroupBy.com.vn.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/getInfo")
    public MessagesResponse GetInfor() {
        MessagesResponse ms = new MessagesResponse();
        ms.data = userService.getInfo();
        return  ms;
    }
    @PutMapping()
    public MessagesResponse Update(@RequestBody UserRes userRes) {
      return    userService.Update(userRes);
    }
    @PutMapping("/updatePass")
    public  MessagesResponse UpdatePass(@RequestBody UpdatePassReq updatePassReq) {
        return  userService.UpdatePass(updatePassReq);
    }
}
