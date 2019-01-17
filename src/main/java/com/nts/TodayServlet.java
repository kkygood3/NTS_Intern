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
 *	dateTimeFormat을 다양하게 써야할 경우 enum으로 분리하여 사용하는 방법이 좋아보임.
 */
@WebServlet("/aboutme/today")
public class TodayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DateTimeFormatter yyyyMMddHHmmFormatter;

	@Override
	public void init() throws ServletException {
		yyyyMMddHHmmFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		LocalDateTime nowDateTime = LocalDateTime.now();
		
		// yyyy/MM/dd HH:mm 형식
		String datetime = nowDateTime.format(yyyyMMddHHmmFormatter);

		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<body>");
		out.println("<a href='/index.html'>메인화면</a>");
		out.print("<h1>");
		out.print("현재시간 : ");
		out.print(datetime);
		out.print("</h1>");
		out.println("</body>");
		out.println("</html>");

	}

}
