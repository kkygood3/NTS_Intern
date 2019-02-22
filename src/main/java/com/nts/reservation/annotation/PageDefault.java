/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.annotation;

import static com.nts.reservation.constant.ParameterDefaultValue.*;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * PageDto Default Value 지정용 애노테이션
 * 
 * 값을 지정해주면 PageDtoArgumentResolver가 PageDto의 멤버변수에 default값을 지정해줍니다.
 * @author jinwoo.bae
 */

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PageDefault{
	String start() default DEFAULT_START;

	String limit() default DEFAULT_LIMIT;
}
