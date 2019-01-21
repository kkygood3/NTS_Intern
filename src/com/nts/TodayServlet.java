/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@WebServlet("/today")
public class TodayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

	/**
	 * 현재 시간을 화면에 출력
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter writer = response.getWriter();
		writer.println("<a href=\"index.html\">메인화면</a>");
		writer.println("<br><br><br><br><br>");
		writer.println("<style type=\"text/css\"> .align-center { text-align: center; } </style>");
		writer.println("<div class=\"align-center\"><font size=\"10\">현재시간 : "
				+ LocalDateTime.now().format(dateTimeFormatter) + "</font></div>");
		writer.close();
	}

}