/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.todolist.common;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public class AlertMessage {

	/**
	 * 서블릿에서 PrintWriter 클래스를 이용해 스크립트를 작성하여 사용자에게 메시지를 출력
	 * @param response HttpServletResponse 객체
	 * @param message 출력 메시지
	 * @param location 출력 후 이동할 페이지
	 */
	public static void showMessage(HttpServletResponse response, String message, String location) throws IOException {
		PrintWriter printWriter = response.getWriter();
		printWriter.println("<script>");
		printWriter.println("alert('" + message + "');");
		printWriter.println("location.href='" + location + "'");
		printWriter.println("</script>");
		printWriter.flush();
	}
}
