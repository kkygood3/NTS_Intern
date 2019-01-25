package com.nts.reservation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"com.nts.reservation.dao", "com.nts.reservation.service", "com.nts.reservation"})
@Import({DBConfig.class})
public class ApplicationConfig {

}
