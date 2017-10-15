package online.shixun.project.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PerformanceAspect {

    //定义annotation切入点，匹配所有 @PerformanceAware 注解的方法
    @Pointcut("@annotation(online.shixun.project.aop.PerformanceAware)")
    public void performancePointcut() {
    }

    //定义@Around通知，在切入方法匹配的方法调用之前、之后记录系统时间以统计匹配方法执行时间
    @Around("performancePointcut()")
    public void monitorPerformance(ProceedingJoinPoint pjp) {
        //方法执行前时间
        long startTime = System.currentTimeMillis();

        try {
            //执行方法
            pjp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }

        //方法执行后时间
        long endTime = System.currentTimeMillis();
        System.out.println(pjp.getSignature() + " 运行时间为:" + (endTime - startTime) / 1000 + "秒。");
    }
}
