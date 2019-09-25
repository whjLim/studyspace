package com.whjlim.springioc.annotation;

import com.whjlim.springioc.xmlbean.MessageProvider;
import com.whjlim.springioc.xmlbean.MessageRender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by BlackWatch on 2019/9/8 16:24
 */
@Service("renderer")
public class StandardOutMessageRender implements  MessageRender{

    private MessageProvider messageProvider;

    @Override
    public void render() {
        if(messageProvider == null){
            throw new RuntimeException(
                    "You must set the property messageProvider of class:"
            + StandardOutMessageRender.class.getName()); //报错的路径
        }
        System.out.println(messageProvider.getMessage());
    }

    @Override
    @Autowired
    public void setMessageProvider(MessageProvider provider) {
        this.messageProvider = provider;
    }

    @Override
    public MessageProvider getMessageProvider() {
        return this.messageProvider;
    }
}
