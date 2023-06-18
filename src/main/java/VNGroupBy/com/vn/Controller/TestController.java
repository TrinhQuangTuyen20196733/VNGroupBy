package VNGroupBy.com.vn.Controller;

import VNGroupBy.com.vn.DTO.response.AuthenticationResponse;
import VNGroupBy.com.vn.Security.JWTService;
import VNGroupBy.com.vn.Security.UserPrincipal;
import VNGroupBy.com.vn.Service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/OAuth2")
public class TestController {
    @Autowired
    private RefreshTokenService refreshTokenService;
    private final JWTService tokenProvider;


    public TestController(JWTService tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @GetMapping("/authentication")
    public AuthenticationResponse OAuth2Authentication(){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        String accessToken = tokenProvider.generateToken(authentication);
        String refreshToken =refreshTokenService.create(userPrincipal.getId()).getToken();
        return new AuthenticationResponse(accessToken,refreshToken);
    }
    @GetMapping("/denied")
    public ResponseEntity<String> OAuth2Denied(){
        return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Xác thực không thành công! Vui lòng thử lại!");
    }
}
