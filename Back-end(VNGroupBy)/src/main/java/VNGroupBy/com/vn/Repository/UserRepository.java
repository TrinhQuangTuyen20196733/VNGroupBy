package VNGroupBy.com.vn.Repository;


import VNGroupBy.com.vn.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    Optional<UserEntity> findByEmailAndPassword(String email, String password);
    UserEntity findByEmail(String email);
}
