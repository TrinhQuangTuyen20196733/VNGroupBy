package VNGroupBy.com.vn.Repository;

import VNGroupBy.com.vn.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;
import java.util.Optional;

public interface ProductRepositoty extends JpaRepository<Product,Long> {
    @Query(value = "select product.* from product " +
            "inner join shop on  product.shop_id = shop.id " +
            "where shop.user_id= ?2 and product.name = ?1", nativeQuery = true)
    Optional<Product> findByNameAndUserId(String productName, Long userId);
}
