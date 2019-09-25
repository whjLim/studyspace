package com.whjlim.springioc.javaconfig;

import com.whjlim.springioc.xmlbean.MessageProvider;
import com.whjlim.springioc.xmlbean.MessageRender;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by BlackWatch on 2019/9/25 10:41
 */
public class HelloWorldSpringAnnotated {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(HelloWorldConfiguration.class);
        MessageRender mr = ctx.getBean("renderer", MessageRender.class);
        MessageProvider mp = ctx.getBean("messageProvider", MessageProvider.class);
        System.out.println(mp == mr.getMessageProvider());
        mr.render();
    }
}
