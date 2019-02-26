/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.commons.debugPrinter;

/**currentThread
 * @Author Duik Park, duik.park@nts-corp.com
 */
public class DebugPrinter {
	public static void print(StackTraceElement stackTraceElement, String msg) {
		System.out.println("[" + stackTraceElement.getFileName()
			+ "] " + stackTraceElement.getMethodName() + "]");
		System.out.println(msg + "\n");
	}
}
