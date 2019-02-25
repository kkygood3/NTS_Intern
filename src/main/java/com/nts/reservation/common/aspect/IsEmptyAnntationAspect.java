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

@Aspect
@Component
public class IsEmptyAnntationAspect {

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
