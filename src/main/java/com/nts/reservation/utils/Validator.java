/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.utils;

public class Validator {
	static final int TOTAL_CATEGORY_COUNT = 5;

	public static boolean isCategoryInvaild(int categoryId, int start) {
		if (categoryId < 0 || categoryId > TOTAL_CATEGORY_COUNT) {
			return true;
		}

		if (start < 0) {
			return true;
		}

		return false;
	}

}
