/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.commons.validator;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public class ArgumentValidator {
	public static boolean checkCategoryId(int categoryId) {
		if (categoryId < 0) {
			String msg = "올바르지 않은 categoryId : " + categoryId;

			System.out.println(msg);
			throw new IllegalArgumentException(msg);
		}
		return true;
	}

	public static boolean checkDisplayInfoId(int displayInfoId) {
		if (displayInfoId <= 0) {
			String msg = "올바르지 않은 displayInfoId : " + displayInfoId;

			System.out.println(msg);
			throw new IllegalArgumentException(msg);
		}
		return true;
	}

	public static boolean checkStartAndLimit(int start, int limit) {
		if (start < 0 || limit < 0) {
			String msg = "올바르지 않은 start : " + start + " 또는 limit" + limit;
			System.out.println(msg);
			throw new IllegalArgumentException(msg);
		}
		return true;
	}
}