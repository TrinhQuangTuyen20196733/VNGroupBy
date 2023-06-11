package VNGroupBy.com.vn.Security;

import java.util.Arrays;
import java.util.List;

public class SecurityConstant {
    public static final String SECRET_KEY= "6E5A7134743777217A25432A462D4A614E645267556B58703273357538782F41";
    public static final long EXPIRATION_TIME = 86400000 * 7; // 7 day

    public static final List<String> authorizedRedirectUris = Arrays.asList("http://localhost:3000/oauth2/redirect", "myandroidapp://oauth2/redirect","myiosapp://oauth2/redirect");
}
