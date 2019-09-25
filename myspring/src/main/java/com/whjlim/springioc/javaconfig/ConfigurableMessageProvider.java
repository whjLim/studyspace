package com.whjlim.springioc.javaconfig;

import com.whjlim.springioc.xmlbean.MessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by BlackWatch on 2019/9/25 13:54
 * 构造函数注入
 */
@Service("provider")
public class ConfigurableMessageProvider implements MessageProvider{

    private String message;

    @Autowired
    public ConfigurableMessageProvider(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
