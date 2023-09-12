package VNGroupBy.com.vn.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "money_transfer")
public class MoneyTransfer extends  BaseEntity{
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_user_id")
    private UserEntity formUser;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_user_id")
    private UserEntity toUser;
    @Column(name = "amount")
    private  int amount;
}
