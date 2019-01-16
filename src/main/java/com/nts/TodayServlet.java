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
 * Servlet implementation class TodayServlet
 */
/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
@WebServlet(name = "today", urlPatterns = {"/today"})
public class TodayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TodayServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");

		LocalDateTime time = LocalDateTime.now();

		DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy/M/d HH:mm");
		String customTime = time.format(timeFormat);

		PrintWriter out = response.getWriter();
		out.println("<link rel='stylesheet' type='text/css' href='css/today.css'>"
				+ "<body class='servlet_body'>"
					+ "<a href='index.html' class='go_main'>메인화면</a>"
					+ "<div class='time_area'>"
						+ "<h1 class='system_time'> 현재시간 : " + customTime + "</h1>"
					+ "</div>"
				+ "</body>");
	}

}