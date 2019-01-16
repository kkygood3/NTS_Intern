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
 * 
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
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		LocalDateTime currentTime = LocalDateTime.now();

		DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy/M/d HH:mm");
		String formattedTime = currentTime.format(timeFormat);

		PrintWriter out = response.getWriter();
		out.println("<link rel='stylesheet' type='text/css' href='css/layout.css'>"
			+ "<body class='servlet_body'>"
			+ "<a href='index.html'>메인화면</a>"
			+ "<div>"
			+ "<h1 class='current_time'> 현재시간 : " + formattedTime + "</h1>"
			+ "</div>"
			+ "</body>");
	}

}