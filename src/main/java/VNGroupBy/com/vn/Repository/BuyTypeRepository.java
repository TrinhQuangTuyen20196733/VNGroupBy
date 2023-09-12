package VNGroupBy.com.vn.Repository;

import VNGroupBy.com.vn.Entity.BuyType;
import VNGroupBy.com.vn.Entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BuyTypeRepository extends JpaRepository<BuyType,Long> {
    @Query(value = "SELECT * FROM buy_type WHERE name= ?1", nativeQuery = true)
    Optional<BuyType> findByName(String name);
}
