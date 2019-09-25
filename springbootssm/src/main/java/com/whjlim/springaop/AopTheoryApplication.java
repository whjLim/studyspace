package com.whjlim.springaop;

import com.whjlim.springaop.service.SPAService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Created by BlackWatch on 2019/9/11 0:32
 */
@SpringBootApplication
public class AopTheoryApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(AopTheoryApplication.class, args);
        SPAService spa = context.getBean(SPAService.class);
        spa.aromaOilMassage("Mike");
        spa.aromaOilMassage("Tony");
        spa.fullBodyMassage("whjlim");

    }
}
