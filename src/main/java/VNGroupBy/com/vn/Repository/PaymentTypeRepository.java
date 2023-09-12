package VNGroupBy.com.vn.Repository;

import VNGroupBy.com.vn.Entity.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;
import java.util.Optional;

public interface PaymentTypeRepository extends JpaRepository<PaymentType,Long> {
    @Query(value = "SELECT * FROM payment_type WHERE name= ?1", nativeQuery = true)
    Optional<PaymentType> findByName(String paymentName);
}
