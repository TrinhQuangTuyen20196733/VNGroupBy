package VNGroupBy.com.vn.Entity;

import VNGroupBy.com.vn.Repository.StudentRepository;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "student")
@Data
public class Student  {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "studentGenerator")
    @SequenceGenerator(name = "studentGenerator",sequenceName = "serialA",initialValue = 10)
    private  long id ;
    private String address;
    private String name;
    private int score;


}
