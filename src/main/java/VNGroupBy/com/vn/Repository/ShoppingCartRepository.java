package VNGroupBy.com.vn.Repository;

import VNGroupBy.com.vn.Entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {
    @Query(value = "SELECT * FROM shopping_cart WHERE user_id= ?1", nativeQuery = true)
    Optional<ShoppingCart> findByUserId(Long userId);
}
