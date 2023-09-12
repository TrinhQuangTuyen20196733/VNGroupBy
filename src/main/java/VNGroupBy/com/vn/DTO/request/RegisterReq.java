package VNGroupBy.com.vn.DTO.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterReq {
    @NotBlank
    public String name;
    @Email
    public String email;
    @NotBlank
    public String password;

    public String phoneNumber;

    public String address;
    public Date birthDay;
    public String role;

}
