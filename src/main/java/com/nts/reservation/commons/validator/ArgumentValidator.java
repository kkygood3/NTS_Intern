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
			System.out.println("올바르지 않은 categoryId : " + categoryId);

			throw new IllegalArgumentException("올바르지 않은 categoryId : " + categoryId);
		}
		return true;
	}

	public static boolean checkDisplayInfoId(int displayInfoId) {
		if (displayInfoId <= 0) {
			System.out.println("올바르지 않은 displayInfoId : " + displayInfoId);

			throw new IllegalArgumentException("올바르지 않은 displayInfoId : " + displayInfoId);
		}
		return true;
	}

	public static boolean checkStartAndLimit(int start, int limit) {
		if (start < 0 || limit < 0) {
			System.out.println("올바르지 않은 start : " + start + " 또는 limit" + limit);

			throw new IllegalArgumentException("올바르지 않은 start : " + start + " 또는 limit" + limit);
		}
		return true;
	}

	public static boolean checkCategoryId(int start, int limit) {
		if (start < 0 || limit < 0) {
			System.out.println("올바르지 않은 start : " + start + " 또는 limit" + limit);

			throw new IllegalArgumentException("올바르지 않은 start : " + start + " 또는 limit" + limit);
		}
		return true;
	}
}
