package com.nts;

/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 현재 시간을 출력해주는 Class
 * 
 * @author yongjoon.park
 */

@WebServlet(urlPatterns = {"/today"})
public class TodayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

	/**
	 * 현재 시간과 그에 해당하는 간단한 페이지 출력
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		PrintWriter writer = response.getWriter();
		writer.print("<!DOCTYPE html>");
		writer.print("<html>");
		writer.print("<head>");
		writer.print("<meta charset=\"UTF-8\">");
		writer.print("<title>현재 시간</title>");
		writer.print("</head>");
		writer.print("<body>");
		writer.print("<a href='index.html'>메인화면</a>");
		writer.print("<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>");
		writer.print("<h1 style='text-align: center;'>현재시간 : " +
			LocalDateTime.now().format(DATE_TIME_FORMATTER) + "</h1>");
		writer.print("</body>");
		writer.print("</html>");
	}
}
