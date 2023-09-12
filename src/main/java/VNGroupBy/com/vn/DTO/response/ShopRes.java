package VNGroupBy.com.vn.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopRes {
    public  Long id;
    public String name;
    public String address;
    public String merchandise;
    public String phoneNumber;
    public String avatarUrl;
}
