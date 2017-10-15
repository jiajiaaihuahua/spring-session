package online.shixun.test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import online.shixun.model.Order;
import online.shixun.model.User;

public class HibernateOneToManyDemo {

    private SessionFactory sessionFactory;

    private Session session;

    private Transaction transaction;

    public static void main(String[] args) {
        HibernateOneToManyDemo hibernateDemo = new HibernateOneToManyDemo();
        hibernateDemo.init();
        hibernateDemo.saveUser();
        
//        hibernateDemo.getUser();
        hibernateDemo.destroy();
    }

    /**
     * 完成Hibernate初始化
     */
    public void init() {
        // 获取配置文件
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        // 注册服务对象
        StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder();
        ServiceRegistry serviceRegistry = ssrb.applySettings(configuration.getProperties()).build();
        // 获取sessionfactory
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        // 获取Session
        session = sessionFactory.openSession();
        // 开启事物
        transaction = session.beginTransaction();
    }

    /**
     * 销毁Hibernate资源
     */
    public void destroy() {
        // 提交事物
        transaction.commit();
        // 关闭 Session
        session.close();
        // 关闭 SessionFactory
        sessionFactory.close();
    }

    /**
     * 查询用户及其订单数据
     */
    public void getUser() {
        User user = (User) session.get(User.class, 1L);
        System.out.println(user);
    }

    /**
     * 保存用户及其关联的订单数据
     */
    public void saveUser() {
        User user = new User("ShiXun", "123456", new Date());
        user.getOrders().add(new Order("100001", new Date()));
        user.getOrders().add(new Order("100002", new Date()));
        user.getOrders().add(new Order("100003", new Date()));
        //执行保存操作
        session.saveOrUpdate(user);
    }

}
