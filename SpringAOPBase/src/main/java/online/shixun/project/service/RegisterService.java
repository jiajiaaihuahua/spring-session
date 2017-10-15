package online.shixun.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.shixun.project.aop.PerformanceAware;
import online.shixun.project.dao.RegisterDao;

//@Service 注解，表明这是一个服务类（处理业务逻辑）
@Service
public class RegisterService {

    private RegisterDao registerDao;

    //使用 @PerformanceAware注解标记的方法都会匹配PerformanceAspect.performancePointcut切入点匹配
    @PerformanceAware
    public void doRegister(String userName) {
        int randomSleepTime = (int) (Math.random() * 5000);
        try {
            Thread.sleep(randomSleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        registerDao.saveUser(userName);
    }

    // 利用构造函数为 RegisterService 类注入 RegisterDao
    // 在 Srping 4.0 以后的版本中，如果构造函数中只有一个参数，以处的@Autowired注解可以省略
    @Autowired
    public RegisterService(RegisterDao registerDao) {
        this.registerDao = registerDao;
    }
}
