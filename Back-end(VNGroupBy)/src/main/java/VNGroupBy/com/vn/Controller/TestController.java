package VNGroupBy.com.vn.Controller;

import VNGroupBy.com.vn.Security.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/OAuth2")
public class TestController {
    private final JWTService tokenProvider;

    public TestController(JWTService tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @GetMapping("/authentication")
    public ResponseEntity<String> OAuth2Authentication(){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        String accessToken = tokenProvider.generateToken(authentication);
        return ResponseEntity.status(HttpStatus.OK).body(accessToken);
    }
    @GetMapping("/denied")
    public ResponseEntity<String> OAuth2Denied(){
        return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Xác thực không thành công! Vui lòng thử lại!");
    }
}
