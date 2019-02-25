package com.nts.reservation.util;

import java.util.regex.Pattern;

public class Utils {
	private Utils() {}
	public static boolean isEmpty(String str) {
		String emptyString = "^\\s*$";
		if (str == null || Pattern.matches(emptyString, str)) {
			return true;
		}
		return false;
	}
	public static boolean isNull(Object obj) {
		if (obj == null) {
			return true;
		}
		return false;
	}
}
