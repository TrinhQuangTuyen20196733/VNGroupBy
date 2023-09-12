package VNGroupBy.com.vn.Controller;

import VNGroupBy.com.vn.DTO.response.MessagesResponse;
import VNGroupBy.com.vn.Entity.Student;
import VNGroupBy.com.vn.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {
    @Autowired
    private StudentRepository studentRepository;
@GetMapping("/test")
    public MessagesResponse oke(){
    Student student = new Student();
    student.setName("Tuyen");
    student.setAddress("Hai Hau");
    student.setScore(9);
    studentRepository.save(student);
    return  null;
};

}
