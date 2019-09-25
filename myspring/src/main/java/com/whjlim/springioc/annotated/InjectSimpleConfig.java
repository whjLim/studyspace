package com.whjlim.springioc.annotated;

import org.springframework.stereotype.Component;

/**
 * Created by BlackWatch on 2019/9/25 15:51
 * 这里使用了@Component 而不是使用@Service注解。基本上，使用@Component 与使用 @Service
 * 具有相同的效果。这两个注释都是告诉Spring,注解的类是使用基于注释的配置和类路径扫描进行自动检测时的候选对象。
 * 但是，由于InjectSimpleConfig类存储了应用程序配置，而不是提供业务服务，因此使用@Component更有意义。
 * 实际上，@Service 是 @Component的一个特例，它表明注解的类正在向应用程序中的其他层提供业务服务
 */
@Component("injectSimpleConfig")
public class InjectSimpleConfig {
    private String name = "san zhang";
    private int age = 37;
    private float hight = 1.84f;
    private boolean programmer = false;
    private Long ageInSeconds = 1_241_401_112L;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public float getHight() {
        return hight;
    }

    public boolean isProgrammer() {
        return programmer;
    }

    public Long getAgeInSeconds() {
        return ageInSeconds;
    }
}
