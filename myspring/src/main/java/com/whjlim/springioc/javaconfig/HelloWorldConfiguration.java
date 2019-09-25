package com.whjlim.springioc.javaconfig;

import com.whjlim.springioc.xmlbean.HelloWorldMessageProvider;
import com.whjlim.springioc.xmlbean.MessageProvider;
import com.whjlim.springioc.xmlbean.MessageRender;
import com.whjlim.springioc.xmlbean.StandardOutMessageRender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by BlackWatch on 2019/9/25 10:36
 */
//@ComponentScan(basePackages = {"com.whjlim.springioc.annotation"})
//@ImportResource(locations = {"spring/app-context-construct.xml"})
@Configuration
public class HelloWorldConfiguration {

    @Bean
    public MessageProvider provider(){
        return new HelloWorldMessageProvider();
    }

    @Bean
    public MessageRender renderer(){
        MessageRender renderer = new StandardOutMessageRender();
        renderer.setMessageProvider(provider());
        return renderer;
    }

}
