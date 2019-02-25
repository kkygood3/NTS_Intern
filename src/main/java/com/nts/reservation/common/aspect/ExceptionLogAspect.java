package com.nts.reservation.common.aspect;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * exception 발생시 로그 작성
 * @author 임상현, life4lord93@nts-corp.com
 */
@Aspect
@Component
public class ExceptionLogAspect {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Before("execution(* com.nts.reservation.common.controlleradvice.ExceptionHandleControllerAdvice.*(..)) && args(e,..)")
	public void writeExceptionLog(JoinPoint joinPoint, Throwable e) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes())
			.getRequest();
		StringBuffer log = new StringBuffer();

		log.append(String.format("DATE : %s, IP : %s, URL : %s\n",
			LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
			request.getRemoteAddr(),
			request.getRequestURL()));

		log.append("Parameters : \n");
		Enumeration<String> enumeration = request.getParameterNames();
		while (enumeration.hasMoreElements()) {
			String parameterName = enumeration.nextElement();
			String parameterValue = request.getParameter(parameterName);
			log.append(String.format("\t%s : %s\n", parameterName, parameterValue));
		}

		log.append("Exception : \n");
		log.append(e);

		log.append("\n\n");
		logger.error(log.toString());
	}
}
