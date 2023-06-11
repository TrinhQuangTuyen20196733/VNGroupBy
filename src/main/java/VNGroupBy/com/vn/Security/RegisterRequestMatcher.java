package VNGroupBy.com.vn.Security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class RegisterRequestMatcher implements RequestMatcher {
    private static final String REGISTER_PATH = "/register";

    @Override
    public boolean matches(HttpServletRequest request) {
        return request.getRequestURI().startsWith(REGISTER_PATH);
    }
}