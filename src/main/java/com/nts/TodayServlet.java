/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 현재 시간을 출력하는 Servlet
@WebServlet("/aboutme/today")
public class TodayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		// 현재 날짜, 시간 획득
		LocalDateTime localDateTime = LocalDateTime.now();

		// 날짜, 시간을 특정 포맷으로 변환
		String datetime = localDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));

		// 출력 통로 획득
		PrintWriter out = response.getWriter();

		// html 코드 출력
		out.println("<html>");
		out.println("<body>");
		out.println("<a href='/index.html'>메인화면</a>");
		out.print("<h1>");
		out.print("현재시간 : " + datetime);
		out.print("</h1>");
		out.println("</body>");
		out.println("</html>");

	}

}
