package com.nts.reservation.interceptor;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.nts.reservation.util.ReservationInputValidator;

public class UserAuthorizationInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		HttpSession session = request.getSession();

		String pathPattern = "^(/reservation)|^(/detail/(\\d)+/comment)";
		if (!Pattern.matches(pathPattern, request.getRequestURI())) {
			return true;
		}

		String email = (String)session.getAttribute("email");
		if (email == null || !ReservationInputValidator.isValidEmail(email)) {
			session.setAttribute("targetUrl", request.getRequestURI());
			response.sendRedirect("/login");
			return false;
		}
		return true;
	}
}
