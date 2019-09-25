package com.whjlim.springioc.xmlbean;

/**
 * Created by BlackWatch on 2019/9/8 16:20
 */
public interface MessageRender {
    void render();
    void setMessageProvider(MessageProvider provider);
    MessageProvider getMessageProvider();
}
