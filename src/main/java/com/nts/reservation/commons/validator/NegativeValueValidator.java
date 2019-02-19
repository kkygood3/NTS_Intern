/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.commons.validator;

public class NegativeValueValidator {

	/**
	 * <pre>
	 * NegativeValueValidator.isNegativeValue(1,1,1)		= false;
	 * NegativeValueValidator.isNegativeValue(-1,1,1)		= true;
	 * NegativeValueValidator.isNegativeValue(1,1,-1)		= true;
	 * NegativeValueValidator.isNegativeValue(1,1,1,1,-1)	= true;
	 * </pre>
	 *
	 * @param 정수값
	 * @return 하나라도 음수가 존재하면 true 반환
	 */
	public static boolean isNegativeValue(int... numbers) {
		for(int number : numbers) {
			if(number < 0) {
				return true;
			}
		}
		return false;
	}

}
