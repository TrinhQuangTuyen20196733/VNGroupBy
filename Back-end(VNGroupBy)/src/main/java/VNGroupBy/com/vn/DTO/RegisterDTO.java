package VNGroupBy.com.vn.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
    private String name;
    private String email;
    private String userName;
    private String password;
    private String phoneNumber;
    private String address;
    private Date birthDay;
}
