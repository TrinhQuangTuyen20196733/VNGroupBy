package VNGroupBy.com.vn.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePassReq {
    public String currentPassword;
    public  String newPassword;
}
