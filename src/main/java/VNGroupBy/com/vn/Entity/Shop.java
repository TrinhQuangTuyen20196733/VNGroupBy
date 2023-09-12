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
@Table(name = "shop")
public class Shop extends BaseEntity {
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @Column(name = "name")
    public String name;
    @Column (name = "address")
    public String address;
    @Column(name = "merchandise")
    public String merchandise;
    @Column(name="phone_number")
    public  String phoneNumber;
    @Column(name="avatar")
    public String avatarUrl;

}
