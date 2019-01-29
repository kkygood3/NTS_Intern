package com.nts.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"com.nts.dao", "com.nts.service"})
@Import({DBConfig.class})
public class ApplicationConfig {

}
