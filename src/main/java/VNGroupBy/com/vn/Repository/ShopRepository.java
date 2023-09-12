package VNGroupBy.com.vn.Repository;


import VNGroupBy.com.vn.Entity.Shop;
import VNGroupBy.com.vn.Entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shop,Long> {

    @Query(value = "SELECT * FROM shop WHERE user_id= ?1", nativeQuery = true)
    Optional<Shop> findByUserId(long id);
}
