package VNGroupBy.com.vn.Repository;

import VNGroupBy.com.vn.Entity.MoneyTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MoneyTransferRepository extends JpaRepository<MoneyTransfer,Long> {
    @Query(value = "SELECT * FROM money_transfer WHERE from_user_id= ?1 OR  to_user_id= ?1 ORDER BY id LIMIT ?2 OFFSET ?3" , nativeQuery = true)
    Optional<List<MoneyTransfer>> getMe(long ID,int page,int offset);
}
