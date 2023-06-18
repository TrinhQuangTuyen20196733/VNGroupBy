package VNGroupBy.com.vn.Entity;

import VNGroupBy.com.vn.SocialMedia.Model.AuthProvider;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity extends  BaseEntity {

    @Column(nullable = false,name = "name")
    private String name;

    @Email
    @Column(nullable = false,name = "email")
    private String email;

    @JsonIgnore
    @Column(name = "imageUrl")
    private String imageUrl;


    @Column(name = "password")
    private String password;

    @Column(name = "provider")
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    @JsonIgnore
    @Column(name = "providerId")
    private String providerId;

    @JsonIgnore
    @Column(name = "isOauth2Account",nullable = false)
    private boolean isOauth2Account = false;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "birthday")
    private Date birthDay;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_code")
    private RoleEntity role;

}



