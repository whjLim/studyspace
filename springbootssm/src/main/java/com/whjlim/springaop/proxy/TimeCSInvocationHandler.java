package com.whjlim.springaop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by BlackWatch on 2019/9/11 1:36
 */
public class TimeCSInvocationHandler implements InvocationHandler{

    private Object target;
    public TimeCSInvocationHandler(Object target){
        super();
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long stime = System.currentTimeMillis();
        Object ret = method.invoke(target,args);
        long useTime = System.currentTimeMillis() - stime;
        System.out.println("记录：" + this.target.getClass() + "." + method.getName() + "耗时：" + (useTime / 1000) + " 秒");
        return ret;
    }
}
