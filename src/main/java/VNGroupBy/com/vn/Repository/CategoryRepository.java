package VNGroupBy.com.vn.Repository;


import VNGroupBy.com.vn.Entity.Category;
import VNGroupBy.com.vn.Entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    @Query(value = "SELECT * FROM category WHERE name= ?1", nativeQuery = true)
    Optional<Category> findByName(String name);
}
