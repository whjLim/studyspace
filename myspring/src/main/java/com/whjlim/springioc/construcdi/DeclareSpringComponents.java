package com.whjlim.springioc.construcdi;

import com.whjlim.springioc.xmlbean.MessageProvider;
import com.whjlim.springioc.xmlbean.MessageRender;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * Created by BlackWatch on 2019/9/25 10:03
 */
public class DeclareSpringComponents {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("spring/app-context-construct.xml");
        ctx.refresh();
        MessageProvider messageProvider = ctx.getBean("provider", MessageProvider.class);
        System.out.println(messageProvider.getMessage());
        MessageRender messageRender = ctx.getBean("renderer", MessageRender.class);
        messageRender.render();
        ctx.close();
    }
}
