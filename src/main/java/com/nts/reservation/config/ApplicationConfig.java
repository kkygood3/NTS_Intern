package com.nts.reservation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import static com.nts.reservation.config.ProjectInfo.*;


/*
 * basepackage 설정
 * @author 시윤
 */
@Configuration
@ComponentScan(basePackages = {BASE_PACKEGE + ".dao", BASE_PACKEGE + ".service"})
@Import({DBConfig.class})
public class ApplicationConfig {

}
