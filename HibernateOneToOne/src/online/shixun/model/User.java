package online.shixun.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 用户信息
 */
@Entity
@Table(name = "t_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 账号
    private String loginName;

    // 密码
    private String password;

    // 用户创建时间
    @Temporal(TemporalType.DATE)
    private Date createDate;

    // 用户关联的会员卡（一个用户只关联一个会员卡）
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    private MembershipCard membershipCard;

    public User(String loginName, String password, Date createDate) {
        super();
        this.loginName = loginName;
        this.password = password;
        this.createDate = createDate;
    }

    public User() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public MembershipCard getMembershipCard() {
        return membershipCard;
    }

    public void setMembershipCard(MembershipCard membershipCard) {
        this.membershipCard = membershipCard;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", loginName=" + loginName + ", password=" + password + ", createDate=" + createDate + "]";
    }

    public String toStringAndCard() {
        return "User [id=" + id + ", loginName=" + loginName + ", password=" + password + ", createDate=" + createDate + ",membershipCard=" + membershipCard + "]";
    }

}
