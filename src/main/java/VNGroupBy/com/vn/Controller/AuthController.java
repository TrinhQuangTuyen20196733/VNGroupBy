package VNGroupBy.com.vn.Controller;

import VNGroupBy.com.vn.DTO.request.LoginRequest;
import VNGroupBy.com.vn.DTO.request.RefreshTokenRq;
import VNGroupBy.com.vn.DTO.response.AuthenticationResponse;
import VNGroupBy.com.vn.Entity.RefreshToken;
import VNGroupBy.com.vn.Entity.UserEntity;
import VNGroupBy.com.vn.Exception.LoginException;
import VNGroupBy.com.vn.Exception.TokenInValid;
import VNGroupBy.com.vn.Security.JWTService;
import VNGroupBy.com.vn.Security.UserDetails;
import VNGroupBy.com.vn.Security.UserPrincipal;
import VNGroupBy.com.vn.Service.RefreshTokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Auth")
public class AuthController {
    @Autowired
    private JWTService tokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;
   @Autowired
    RefreshTokenService refreshTokenService;
    @Autowired
    JWTService jwtService;

    @PostMapping("/login")
    public AuthenticationResponse Login(@RequestBody @Valid LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );
            var userPrincipal = (UserDetails) authentication.getPrincipal();
            var userID = userPrincipal.getUser().getId();
            String accessToken = tokenProvider.generateToken(userID);
            String refreshToken =refreshTokenService.create(userID).getToken();
            return new AuthenticationResponse(accessToken,refreshToken);
        }
        catch (Exception e) {
            throw  new LoginException("Login Fail! Please, try again!");
        }




    }

    @PostMapping("/refreshToken")
    public AuthenticationResponse RefreshToken(@RequestBody @Valid RefreshTokenRq refreshTokenRq) {
        var refresh_token = refreshTokenRq.getRefresh_token();
        var token = refreshTokenService.findByToken(refresh_token)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(UserEntity::getId)
                .map(jwtService::generateToken)
                .orElseThrow(() -> new TokenInValid("Access token generation failed"));
        return new AuthenticationResponse(token, refresh_token);


    }
}
