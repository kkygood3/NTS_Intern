/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package main.java.com.nts;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * TodayServlet.java
 * @Author Duik Park, duik.park@nts-corp.com
 */
@WebServlet("/today")
public class TodayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DateTimeFormatter dateTimeFormatter;

	@Override
	public void init() {
		dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
	}

	/**
	 * 현재 시간을 화면에 출력
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<a href=\"index.html\">메인화면</a>");

		out.println(
				"<style type=\"text/css\"> #center { position: absolute; top:50%; left: 50%; margin-left: -300px; } </style>");

		out.println("<nav id=\"center\"><font size=\"10\">현재시간 : " + LocalDateTime.now().format(dateTimeFormatter)
				+ "</font></nav>");
		out.close();
	}

}