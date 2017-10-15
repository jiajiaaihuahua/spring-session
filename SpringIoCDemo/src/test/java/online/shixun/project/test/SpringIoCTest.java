package online.shixun.project.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import online.shixun.project.service.RegisterService;

//@Component 注解，表明这是一个组件类
@Component
public class SpringIoCTest {

    //自动注入注册服务类
    @Autowired
    private RegisterService service;

    public static void main(String[] args) {
        //初始化 ApplicationContext
        ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml" });

        //SpringIoCTest 实例必需通过 IoC 容器获得，否则不能在类中使用 @Autowired 方式注入其它 Bean
        SpringIoCTest iocTest = ctx.getBean(SpringIoCTest.class);

        iocTest.testRegister();
        
        //关闭 ApplicationContext
        ((ClassPathXmlApplicationContext) ctx).close();
    }

    //测试注册服务类中的：doRegister 方法
    public void testRegister() {
        service.doRegister();
    }
}
