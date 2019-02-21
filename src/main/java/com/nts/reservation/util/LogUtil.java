/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author 육성렬
 */
public class LogUtil {

	public static String convertStackTraceToString(Exception exception) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		exception.printStackTrace(printWriter);
		return stringWriter.toString();
	}
}
