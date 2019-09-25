package com.whjlim.springaop.proxy;

import java.lang.reflect.Proxy;

/**
 * Created by BlackWatch on 2019/9/11 1:34
 */
public class DynamicProxy {
    public static <T> T getProxy(Object target, Class<T> clazz){
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), target.getClass().getInterfaces(),
                new TimeCSInvocationHandler(target));
    }
}
