package VNGroupBy.com.vn.Controller;

import VNGroupBy.com.vn.DTO.response.AuthenticationResponse;
import VNGroupBy.com.vn.Exception.InternalServerException;
import VNGroupBy.com.vn.Security.JWTService;
import VNGroupBy.com.vn.Security.UserPrincipal;
import VNGroupBy.com.vn.Service.RefreshTokenService;
import VNGroupBy.com.vn.Utils.Encrytion.TokenEncryption;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/OAuth2")
public class OAuth2Controller {
    @Autowired
    private RefreshTokenService refreshTokenService;
    private final JWTService tokenProvider;


    public OAuth2Controller(JWTService tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @GetMapping("/authentication")
    public void OAuth2Authentication( HttpServletResponse response) throws IOException {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        String accessToken = tokenProvider.generateToken(authentication);
        String refreshToken =refreshTokenService.create(userPrincipal.getId()).getToken();
        String token = accessToken+";**"+refreshToken;
        response.sendRedirect("http://localhost:3000/SuccessAuthen?token="+token);
    }

    @GetMapping("/denied")
    public ResponseEntity<String> OAuth2Denied(){
        return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Xác thực không thành công! Vui lòng thử lại!");
    }
}
