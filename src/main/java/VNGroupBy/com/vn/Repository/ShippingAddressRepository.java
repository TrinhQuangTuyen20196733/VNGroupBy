package VNGroupBy.com.vn.Repository;

import VNGroupBy.com.vn.Entity.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ShippingAddressRepository extends JpaRepository<ShippingAddress,Long> {
    @Query(value = "SELECT * FROM shipping_address WHERE user_id= ?1", nativeQuery = true)
    Optional<ShippingAddress> findByUserId(Long userId);
}
