package online.shixun.project.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import online.shixun.project.service.RegisterService;

//@Component 注解，表明这是一个组件类
@Component
public class SpringAOPTest {

    //自动注入注册服务类
    @Autowired
    private RegisterService registerService;

    public static void main(String[] args) {
        //初始化 ApplicationContext
        ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml" });

        //SpringIoCTest 实例必需通过 IoC 容器获得，否则不能在类中使用 @Autowired 方式注入其它 Bean
        SpringAOPTest aopTest = ctx.getBean(SpringAOPTest.class);

        aopTest.testRegister();

        //关闭 ApplicationContext
        ((ClassPathXmlApplicationContext) ctx).close();
    }

    //测试注册服务类中的：doRegister 方法
    public void testRegister() {
        registerService.doRegister("轻实训");
    }
}
