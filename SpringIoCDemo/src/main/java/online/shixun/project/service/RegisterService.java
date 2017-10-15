package online.shixun.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.shixun.project.dao.RegisterDao;

//@Service 注解，表明这是一个服务类（处理业务逻辑）
@Service
public class RegisterService {

    private RegisterDao registerDao;

    public void doRegister() {
        registerDao.saveUser();
    }

    //利用构造函数为 RegisterService 类注入 RegisterDao
    //在 Srping 4.0 以后的版本中，如果构造函数中只有一个参数，以处的@Autowired注解可以省略
    @Autowired
    public RegisterService(RegisterDao registerDao) {
        this.registerDao = registerDao;
    }
}
