/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.project3.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Service, Repository Config
 */
@Configuration
@ComponentScan({"com.nts.project3.dao", "com.nts.project3.service"})
@Import({DBConfig.class})
public class ApplicationConfig {

}
