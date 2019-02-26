/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.common.aspect;

import java.util.Collection;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.nts.reservation.common.exception.NotFoundDataException;

/**
 * annotation의 기능 구현
 * @author 임상현, life4lord93@nts-corp.com
 */
@Aspect
@Component
public class AnnotationAspect {

	/**
	 * IsEmpty annotation 기능
	 */
	@AfterReturning(pointcut = "@annotation(com.nts.reservation.common.annotation.IsEmpty) && execution(* *(..))", returning = "object")
	public void isEmpty(JoinPoint joinPoint, Object object) {
		if (object instanceof Collection<?>) {
			Collection<?> collection = (Collection<?>)object;
			if (ObjectUtils.isEmpty(collection)) {
				throw new NotFoundDataException();
			}
		}
	}
}
