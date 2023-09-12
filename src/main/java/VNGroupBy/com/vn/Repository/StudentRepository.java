package VNGroupBy.com.vn.Repository;

import VNGroupBy.com.vn.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
