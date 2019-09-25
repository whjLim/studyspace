package com.whjlim.springioc.xmlbean;

/**
 * Created by BlackWatch on 2019/9/8 16:24
 */
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
    public void setMessageProvider(MessageProvider provider) {
        this.messageProvider = provider;
    }

    @Override
    public MessageProvider getMessageProvider() {
        return this.messageProvider;
    }
}
