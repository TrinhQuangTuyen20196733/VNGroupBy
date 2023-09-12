package VNGroupBy.com.vn.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "wallet")
public class Wallet extends  BaseEntity {
    @Column(name = "money")
    private Integer money;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public void addMoney(Integer money) {
        this.money += money;
    }

    public void subMoney(Integer money) {
        this.money -= money;
    }
    public Wallet(UserEntity user) {
        this.user = user;
        this.money = 0;
    }
}
