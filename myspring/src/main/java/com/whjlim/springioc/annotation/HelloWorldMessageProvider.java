package com.whjlim.springioc.annotation;

import com.whjlim.springioc.xmlbean.MessageProvider;
import org.springframework.stereotype.Component;

/**
 * Created by BlackWatch on 2019/9/8 16:23
 */
@Component("provider")
public class HelloWorldMessageProvider implements MessageProvider{

    @Override
    public String getMessage() {
        return "Hello World";
    }
}
