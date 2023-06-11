package VNGroupBy.com.vn.Security.OAuth2;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.SerializationUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Base64;
import java.util.UUID;

@Component
public class HttpSessionOAuth2AuthorizationRequestRepository implements AuthorizationRequestRepository<OAuth2AuthorizationRequest> {
    private static final String SESSION_ATTR_AUTHORIZATION_REQUEST = "authorizationRequest";

    private static final String STATE_PARAMETER = "state";
     private static final String SESSION_ATTR_ORIGINAL_REQUEST = "savedRequest";

    private final String stateToRedirectUriAttributeName = HttpSessionOAuth2AuthorizationRequestRepository.class.getName()
            + ".STATE_TO_REDIRECT_URI";

    private final String stateToRegistrationIdAttributeName = HttpSessionOAuth2AuthorizationRequestRepository.class.getName()
            + ".STATE_TO_REGISTRATION_ID";
    private static final String REGISTER_PATH = "/register";
    @Override
    public OAuth2AuthorizationRequest loadAuthorizationRequest(HttpServletRequest request) {

        Assert.notNull(request, "request cannot be null");
        HttpSession session = request.getSession(false);
        if (session != null) {
            String encodedAuthorizationRequest = (String) session.getAttribute(SESSION_ATTR_AUTHORIZATION_REQUEST);
            if (encodedAuthorizationRequest != null) {
                return deserializeAuthorizationRequest(encodedAuthorizationRequest);
            }
        }
        return null;
    }

    @Override
    public void saveAuthorizationRequest(OAuth2AuthorizationRequest authorizationRequest, HttpServletRequest request, HttpServletResponse response) {

        Assert.notNull(request, "request cannot be null");
        Assert.notNull(response, "response cannot be null");
        if (authorizationRequest == null) {
            removeAuthorizationRequest(request, response);
            return;
        }
        HttpSession session = request.getSession(true);
        session.setAttribute(SESSION_ATTR_AUTHORIZATION_REQUEST, serializeAuthorizationRequest(authorizationRequest));

    }


    @Override
    public OAuth2AuthorizationRequest removeAuthorizationRequest(HttpServletRequest request, HttpServletResponse response) {
        Assert.notNull(request, "request cannot be null");
        HttpSession session = request.getSession(false);
        if (session == null) {
            return null;
        }
        OAuth2AuthorizationRequest authorizationRequest = loadAuthorizationRequest(request);
        session.removeAttribute(SESSION_ATTR_AUTHORIZATION_REQUEST);
        if (response != null) {
            removeStateParameter(request,response);
        }
        return authorizationRequest;
    }
    private OAuth2AuthorizationRequest deserializeAuthorizationRequest(String encodedAuthorizationRequest) {
        try {
            byte[] bytes = Base64.getUrlDecoder().decode(encodedAuthorizationRequest);
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (OAuth2AuthorizationRequest) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new IllegalArgumentException("Failed to deserialize authorization request", e);
        }
    }
    private String serializeAuthorizationRequest(OAuth2AuthorizationRequest authorizationRequest) {
        return Base64.getUrlEncoder().encodeToString(SerializationUtils.serialize(authorizationRequest));
    }
    private void removeStateParameter(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(stateToRedirectUriAttributeName);
            session.removeAttribute(stateToRegistrationIdAttributeName);
        }
    }

    public static String createStateKey(String registrationId, String redirectUri) {
        return UUID.randomUUID().toString() + "_" + registrationId + "_" + redirectUri;
    }
}
