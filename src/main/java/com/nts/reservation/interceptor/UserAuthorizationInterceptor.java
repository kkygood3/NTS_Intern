package com.nts.reservation.interceptor;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.nts.reservation.util.ReservationInputValidator;
import com.nts.reservation.util.Utils;

public class UserAuthorizationInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		HttpSession session = request.getSession();

		if (!requiresAuthorize(request.getRequestURI())) {
			return true;
		}
		
		String email = (String)session.getAttribute("email");
		if (!ReservationInputValidator.isValidEmail(email)) {
			response.sendRedirect("/login");
			return false;
		}
		return true;
	}
	
	private boolean requiresAuthorize(String path) {
		String pathPattern = "^(/reservation)|^(/detail/(\\d)+/comment)";
		return Pattern.matches(pathPattern, path) ? true : false;
	}
}
