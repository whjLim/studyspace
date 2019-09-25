package com.whjlim.springaop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by BlackWatch on 2019/9/11 0:25
 */
@Aspect
@Component
public class TimeConsumingStatisticsAspect {

    /*
    * 没反应
    * This advice advises no methods
    * 搜索一下没反应，有看到
    * spring AOP使用看到有写配置的
    * spring boot AOP使用
    * */
    @Around("execution(* com.whjlim.springaop.service.*.*(..))")
    public Object methodTimeConsumingStatistic(ProceedingJoinPoint pjp) throws Throwable {
        long stime = System.currentTimeMillis();
        Object ret = pjp.proceed();
        long useTime = System.currentTimeMillis() - stime;
        System.out.println("记录：" + pjp.getTarget() + "." + pjp.getSignature() + "耗时：" + (useTime/1000));
        return ret;
    }
}
