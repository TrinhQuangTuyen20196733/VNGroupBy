package VNGroupBy.com.vn.Repository;

import VNGroupBy.com.vn.Entity.UserEntity;
import VNGroupBy.com.vn.Entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet,Long> {
    @Query(value = "SELECT * FROM wallet WHERE user_id= ?1", nativeQuery = true)
    Optional<Wallet> findByUserId(long id);
}
