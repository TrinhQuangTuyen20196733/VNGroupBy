package VNGroupBy.com.vn.Security.OAuth2;

import VNGroupBy.com.vn.DTO.response.AuthenticationResponse;
import VNGroupBy.com.vn.Exception.InternalServerException;
import VNGroupBy.com.vn.Security.JWTService;
import VNGroupBy.com.vn.Security.UserPrincipal;
import VNGroupBy.com.vn.Service.RefreshTokenService;
import VNGroupBy.com.vn.Utils.Encrytion.TokenEncryption;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private JWTService tokenProvider;
    @Autowired
    private RefreshTokenService refreshTokenService;


    @Autowired
    OAuth2AuthenticationSuccessHandler(JWTService tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        HttpSession session = request.getSession(true);
        DefaultSavedRequest savedRequest = (DefaultSavedRequest) session.getAttribute("SPRING_SECURITY_SAVED_REQUEST");
        if (savedRequest == null) {
            super.onAuthenticationSuccess(request, response, authentication);
        }
        String targetUrl = savedRequest.getRedirectUrl();
        if (targetUrl != null && !targetUrl.startsWith(request.getContextPath())) {
            // Nếu là trang bên ngoài, chuyển hướng đến trang mặc định
            targetUrl = getDefaultTargetUrl();
        }
        session.removeAttribute("SPRING_SECURITY_SAVED_REQUEST");

            response.sendRedirect(targetUrl);


    }


}
