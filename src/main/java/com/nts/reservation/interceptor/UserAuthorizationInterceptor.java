package com.nts.reservation.interceptor;

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

		String email = (String) session.getAttribute("email");
		if (!ReservationInputValidator.isValidEmail(email)) {
			response.sendRedirect("/login");
			return false;
		}
		return true;
	}
}
