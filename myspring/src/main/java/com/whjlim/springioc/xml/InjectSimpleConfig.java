package com.whjlim.springioc.xml;

import org.springframework.stereotype.Component;

/**
 * Created by BlackWatch on 2019/9/25 15:51
 */
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
