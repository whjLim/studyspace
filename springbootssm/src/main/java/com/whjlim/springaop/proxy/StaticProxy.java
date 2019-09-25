package com.whjlim.springaop.proxy;

import com.whjlim.springaop.service.KtvService;

/**
 * Created by BlackWatch on 2019/9/11 1:29
 */
public class StaticProxy implements KtvService{

    private KtvService target;
    public StaticProxy(KtvService target){
        super();
        this.target = target;
    }

    @Override
    public void momoSing(String customer) {
        long stime = System.currentTimeMillis();
        this.target.momoSing(customer);
        long useTime = System.currentTimeMillis() - stime;
        System.out.println("记录：" + this.target.getClass() + ".momoSing 耗时：" + (useTime / 1000) + " 秒");
    }
}
