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

/**
 *  /aboutme/today url로 접근시 현재 시간이 출력되는 페이지를 응답하는 Servlet
 *   
 *	TODO dateTimeFormat을 다양하게 써야할 경우 enum으로 분리하여 사용하는 방법이 좋아보임.
 */
@WebServlet("/aboutme/today")
public class TodayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DateTimeFormatter dateTimeFormatter;

	@Override
	public void init() throws ServletException {
		// 날짜, 시간을 일정한 포맷으로 변환시켜주는 dateTimeFormatter 객체 생성
		dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		// 현재 날짜, 시간 획득
		LocalDateTime localDateTime = LocalDateTime.now();

		// 날짜, 시간을 특정 포맷의 문자열로 변환
		String datetime = localDateTime.format(dateTimeFormatter);

		PrintWriter out = response.getWriter();

		// 현재 시간을 출력하는 html 문서 print
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
