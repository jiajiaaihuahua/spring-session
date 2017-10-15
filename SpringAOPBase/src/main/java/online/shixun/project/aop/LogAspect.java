package online.shixun.project.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//切面定义类自身必需声明为一个Bean，否则切面无效
@Component
//定义LogAspect为切面类，切面类中可以包含 切入点(Pointcut)、与通知(advice)
@Aspect
public class LogAspect {

    //声明切入点(匹配所有online.shixun.project.dao.RegisterDao类中所有以save开头，访问修饰符为public，任意类型返回值，包含一个String类型参数的方法)
    @Pointcut("execution(public * online.shixun.project.dao.RegisterDao.save*(String))")
    public void logAllSaveMethod() {
    }

    //声明通知，在logAllSaveMethod()切入点所匹配的方法被调用之前调用
    @Before("logAllSaveMethod()")
    public void logInsertInfo(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature() + " 方法被调用\n" + "方法参数为：" + joinPoint.getArgs()[0].toString());
    }
}
