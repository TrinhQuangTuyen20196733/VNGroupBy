package VNGroupBy.com.vn.Controller;

import VNGroupBy.com.vn.DTO.request.RefreshTokenRq;
import VNGroupBy.com.vn.DTO.response.AuthenticationResponse;
import VNGroupBy.com.vn.Entity.RefreshToken;
import VNGroupBy.com.vn.Entity.UserEntity;
import VNGroupBy.com.vn.Exception.TokenInValid;
import VNGroupBy.com.vn.Security.JWTService;
import VNGroupBy.com.vn.Service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Auth")
public class AuthController {
    @Autowired
    RefreshTokenService refreshTokenService;
    @Autowired
    JWTService jwtService;
    @PostMapping("/refreshToken")
    public AuthenticationResponse RefreshToken(@RequestBody  RefreshTokenRq refreshTokenRq){
        var refresh_token= refreshTokenRq.getRefresh_token();
       var token= refreshTokenService.findByToken(refresh_token)
               .map(refreshTokenService::verifyExpiration)
               .map(RefreshToken::getUser)
               .map(UserEntity::getId)
               .map(jwtService::generateToken)
               .orElseThrow(() -> new TokenInValid("Access token generation failed"));
       return  new AuthenticationResponse(token,refresh_token);


    }
}
