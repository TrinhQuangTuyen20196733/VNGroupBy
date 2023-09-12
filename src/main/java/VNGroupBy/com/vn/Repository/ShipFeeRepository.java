package VNGroupBy.com.vn.Repository;

import VNGroupBy.com.vn.Entity.ShipFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ShipFeeRepository extends JpaRepository<ShipFee,Long> {
    @Query(value = "SELECT * FROM ship_fee WHERE name= ?1", nativeQuery = true)
    Optional<ShipFee> findByName(String shipName);
}
