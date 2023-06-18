package VNGroupBy.com.vn.Service.Impl;

import VNGroupBy.com.vn.Entity.RefreshToken;
import VNGroupBy.com.vn.Exception.TokenInValid;
import VNGroupBy.com.vn.Repository.RefreshTokenRepository;
import VNGroupBy.com.vn.Repository.UserRepository;
import VNGroupBy.com.vn.Security.SecurityConstant;
import VNGroupBy.com.vn.Service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Override
    public RefreshToken create(Long id) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(userRepository.findById(id).get());
        refreshToken.setExpiryDate(Instant.now().plusSeconds(SecurityConstant.REFRESH_TOKEN_EXPIRATION_TIME));
        refreshToken.setToken(UUID.randomUUID().toString());
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenInValid(token.getToken() + " Refresh token was expired. Please make a new signin request");
        }
        return token;
    }

}
