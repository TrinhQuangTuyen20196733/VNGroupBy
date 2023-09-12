package VNGroupBy.com.vn.DTO.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRes {
    public  int id;
    public String name;
    public String email;
    public String password;

    public String phoneNumber;

    public String address;
    public Date birthDay;
    public String role;

}
