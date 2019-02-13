/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.commons.validator;

public class NegativeValueValidator {

	public static boolean isNegativeValue(int first) {
		if(first < 0) {
			return true;
		}
		return false;
	}

	public static boolean isNegativeValue(int first, int second) {
		if(first < 0 || second < 0) {
			return true;
		}
		return false;
	}

	public static boolean isNegativeValue(int first, int second, int third) {
		if(first < 0 || second < 0 || third < 0) {
			return true;
		}
		return false;
	}
}
