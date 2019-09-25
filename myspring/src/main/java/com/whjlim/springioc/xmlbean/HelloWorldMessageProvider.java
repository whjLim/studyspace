package com.whjlim.springioc.xmlbean;

/**
 * Created by BlackWatch on 2019/9/8 16:23
 */
public class HelloWorldMessageProvider implements MessageProvider{

    @Override
    public String getMessage() {
        return "Hello World";
    }
}
