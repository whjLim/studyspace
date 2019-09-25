package com.whjlim.springioc.xmlbean;

import java.util.Properties;

/**
 * Created by BlackWatch on 2019/9/8 16:35
 */
public class MessageSupportFactory {
    private static MessageSupportFactory instance;

    private Properties prop;
    private MessageRender renderer;
    private MessageProvider provider;

    private MessageSupportFactory(){
        prop = new Properties();
        try {
            prop.load(this.getClass().getResourceAsStream("/static/msf.properties"));
            String rendererClass = prop.getProperty("renderer.class");
            String providerClass = prop.getProperty("provider.class");
            renderer = (MessageRender) Class.forName(rendererClass).newInstance();
            provider = (MessageProvider) Class.forName(providerClass).newInstance();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    static {
        instance = new MessageSupportFactory();
    }

    public static MessageSupportFactory getInstance(){
        return instance;
    }

    public MessageRender getMessageRender(){
        return renderer;
    }

    public MessageProvider getMessageProvider(){
        return provider;
    }

}
