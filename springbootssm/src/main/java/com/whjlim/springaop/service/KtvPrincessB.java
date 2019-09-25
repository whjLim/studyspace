package com.whjlim.springaop.service;

import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by BlackWatch on 2019/9/11 0:22
 */
@Service
public class KtvPrincessB implements KtvService{

    private Random random = new Random();

    private int bound = 5;

    @Override
    public void momoSing(String customer) {
        try {
            TimeUnit.SECONDS.sleep(random.nextInt(bound));
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(customer + "享受完momoSing服务！");
    }
}
