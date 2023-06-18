package VNGroupBy.com.vn.Service;

import VNGroupBy.com.vn.Entity.RefreshToken;

import java.util.Optional;

public interface RefreshTokenService {
    RefreshToken create(Long id);
    Optional<RefreshToken> findByToken(String token);
    RefreshToken verifyExpiration(RefreshToken refreshToken);
}
