package online.shixun.test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import online.shixun.model.Order;
import online.shixun.model.Status;

public class HibernateManyToOneTest {

    private SessionFactory sessionFactory;

    private Session session;

    private Transaction transaction;

    public static void main(String[] args) {
        HibernateManyToOneTest hibernateTest = new HibernateManyToOneTest();
        hibernateTest.init();
//        hibernateTest.saveOrder();
        hibernateTest.getOrder();
        hibernateTest.destroy();
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
        // 开启事务
        transaction = session.beginTransaction();
    }

    /**
     * 销毁Hibernate资源
     */
    public void destroy() {
        // 提交事务
        transaction.commit();
        // 关闭 Session
        session.close();
        // 关闭 SessionFactory
        sessionFactory.close();
    }

    /**
     * 查询订单
     */
    public void getOrder() {
        Order order = (Order) session.get(Order.class, 1);
        System.out.println(order);
    }

    /**
     * 保存订单
     */
    public void saveOrder() {
        Order order = new Order("100001", new Date());
        Status status = new Status("正常", new Date());
        order.setStatus(status);
        session.saveOrUpdate(order);
    }

}
