/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.aboutme;

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
 * 브라우저화면에 현재 시간을 나타내주는 서블릿 클래스
 * @author 배진우
 */
@WebServlet("/today")
public class TodayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<head>");
		out.println("<link rel='stylesheet' href='./css/style.css'>");
		out.println("</head>");
		out.println("<body style='background-color: white'>");
		out.println("<a href='index.html'>메인화면</a>");
		out.println("<p id='today'>");
		out.println("현재시간 : " + getToday());
		out.println("</p>");
		out.println("</body>");
		out.close();
	}

	private String getToday() {
		LocalDateTime time = LocalDateTime.now();
		String formatTimeString = time.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
		return formatTimeString;
	}
}
