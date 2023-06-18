package VNGroupBy.com.vn.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterReq {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private Date birthDay;
    private String role;

}
