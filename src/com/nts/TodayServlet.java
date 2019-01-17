/**
 * Copyright 2019 Naver Corp. All rights Reserved.
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

@WebServlet("/today")
public class TodayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/*
	 * 현재 시간을 표시
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.print("<meta charset='utf-8'>");
		out.print("<link rel='stylesheet' href='./css/layout.css'>");
		out.print("<link rel='stylesheet' href='./css/today.css'>");

		out.print("<!doctype html>");
		out.print("<html>");
		out.print("<head>");
		out.print("<title>now time</title>");
		out.print("</head>");

		out.print("<body>");
		out.print("	<header>");
		out.print("		<ul>");
		out.print("			<li><a href='./index.html'>홈</a></li>");
		out.print("			<li><a href='./aboutme.html'>자기소개</a></li>");
		out.print("			<li><a href='./photo.html'>내사진</a></li>");
		out.print("			<li><a href='./today'>몇시에요</a></ul>");
		out.print("		</ul>");
		out.print("	</header>");
		out.print("	<div class='content'>");
		out.print(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-M-d HH:mm")));
		out.print("	</div>");
		out.print("	<footer>");
		out.print("		<p>crong@codesdj.fff</p>");
		out.print("		<p>http://aaa.aaa.aaa</p>");
		out.print("	</footer>");
		out.print("</body>");
		out.print("</html>");

	}

}
