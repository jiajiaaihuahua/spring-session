package online.shixun.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 订单状态
 */
@Entity
@Table(name = "t_status")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 状态名称
    private String name;

    // 用户创建时间
    @Temporal(TemporalType.DATE)
    private Date createDate;

    public Status(String name, Date createDate) {
        super();
        this.name = name;
        this.createDate = createDate;
    }

    public Status() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Status [id=" + id + ", name=" + name + ", createDate=" + createDate + "]";
    }

}
