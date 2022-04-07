package com.mzlxl.jdbc.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;

/**
 * @author mzlxl
 * @date 2022/04/06
 **/
public class ApplicationContextHolder implements ApplicationContextInitializer<ConfigurableApplicationContext>,
    Ordered {

    private static ApplicationContext context;

    public ApplicationContext get() {
        return context;
    }

    public void initialize(ConfigurableApplicationContext applicationContext) {
        context = applicationContext;
    }

    public int getOrder() {
        return -2147483647;
    }
}
