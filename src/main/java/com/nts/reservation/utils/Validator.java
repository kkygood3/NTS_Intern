/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.utils;

public class Validator {
	
	public static boolean isCategoryInvaild(int categoryId, int start) {
		if(categoryId < 0 || categoryId > 5) {
			return true;
		}
		
		if(start < 0) {
			return true;
		}
		
		return false;
	}
	
}
