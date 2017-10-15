package online.shixun.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 会员卡信息
 */
@Entity
@Table(name = "t_card")
public class MembershipCard {

    // 卡号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer number;

    // 余额
    private Double money;

    // 积分
    private Integer point;

    // 会员卡关联用户
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public MembershipCard(Double money, Integer point) {
        super();
        this.money = money;
        this.point = point;
    }

    public MembershipCard() {
        super();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "MembershipCard [number=" + number + ", money=" + money + ", point=" + point + "]";
    }

}
