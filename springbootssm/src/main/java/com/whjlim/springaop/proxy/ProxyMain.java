package com.whjlim.springaop.proxy;

import com.whjlim.springaop.service.KtvPrincessB;
import com.whjlim.springaop.service.KtvService;
import com.whjlim.springaop.service.SPAService;
import com.whjlim.springaop.service.SpaPrincessA;

/**
 * Created by BlackWatch on 2019/9/11 1:33
 */
public class ProxyMain {
    public static void main(String[] args) {
        KtvService pb = new KtvPrincessB();
        KtvService proxy = new StaticProxy(pb);
        proxy.momoSing("Mike");

        System.out.println("----------dynamic proxy-------------");
        KtvService dproxy1 = DynamicProxy.getProxy(pb, KtvService.class);
        dproxy1.momoSing("mike");

        SPAService spaService = DynamicProxy.getProxy(new SpaPrincessA(), SPAService.class);
        spaService.aromaOilMassage("nick");


    }
}
