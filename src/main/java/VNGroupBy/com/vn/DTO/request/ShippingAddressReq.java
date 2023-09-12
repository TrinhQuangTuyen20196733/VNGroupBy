package VNGroupBy.com.vn.DTO.request;

import VNGroupBy.com.vn.Entity.UserEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShippingAddressReq {
    public  long id;
    public String name;
    public String phoneNumber;
    public Boolean isPrivate;
    public  Boolean isDefault;
    public String address;

}
