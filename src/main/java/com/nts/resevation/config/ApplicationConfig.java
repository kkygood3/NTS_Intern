/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.resevation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 스프링 설정파일로 web.xml에 이 클래스가 등록되있으며 
 * ComponentScan를 통해 해당 경로아래의 모든 bean들을 등록해줍니다.
 * @author jinwoo.bae
 */
@Configuration
@ComponentScan({"com.nts.resevation.dao", "com.nts.resevation.service"})
@Import({DBConfig.class})
public class ApplicationConfig {}