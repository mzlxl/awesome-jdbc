package com.mzlxl.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

/**
 * @author mxlxl
 * @date 2022/04/06
 **/
@Slf4j
@Configuration
@AutoConfigureAfter({DataSourceAutoConfiguration.class})
public class AwesomeJdbcAutoConfiguration implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("awesome-jdbc starting...");
    }

}
