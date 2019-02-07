/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.util;

/**
 * @author 전연빈
 */
public class CheckProductParameter {

	/**
	 * @desc start값이 음수인 잘못된 요청
	 * @param start
	 * @return
	 */
	public static boolean isInvalidStart(int start) {
		return start < 0;
	}
}
