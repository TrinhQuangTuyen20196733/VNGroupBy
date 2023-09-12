package VNGroupBy.com.vn.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shipping_address")
public class ShippingAddress extends  BaseEntity {
    private String name;
    private String phoneNumber;
    private Boolean isPrivate;
    private  Boolean isDefault;
    private String address;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST,
    })
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
