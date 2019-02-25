package com.nts.reservation.commons.validator;

import java.util.logging.Logger;

public class LoginEmailValidator {
	private final static Logger LOG = Logger.getGlobal();

	/**
	 * 요청 Email의 정규식을 검증하고 login된 Email과 일치 여부를 검증
	 * @param reservationEmail
	 * @param loginEmail
	 */
	public static boolean isValidEmail(String reservationEmail, String loginEmail) {
		String emailReg = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";

		// 정규식을 활용하여 이메일값을 검증하고, session에 들어있는 이메일값과 비교한다. 유효하지 않으면 false 반환
		if (loginEmail == null || !reservationEmail.matches(emailReg)) {
			LOG.warning("email이 null이거나 형식에 맞지않습니다 email : " + reservationEmail);
			return false;
		}

		if (!reservationEmail.equals(loginEmail.toString())) {
			return false;
		}
		return true;
	}

	public static boolean isInValidEmail(String reservationEmail, String loginEmail) {
		return !isValidEmail(reservationEmail, loginEmail);
	}
}
