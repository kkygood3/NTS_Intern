/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package main.java.com.nts;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.time.LocalDateTime;

@WebServlet("/today")
public class today extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static LocalDateTime now;
	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		now = LocalDateTime.now();
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
		out.print(String.format("<h1>현재시간 : %d/%d/%d %d:%d<h1>", now.getYear(), now.getMonthValue(),
				now.getDayOfMonth(), now.getHour(), now.getMinute()));
		out.print("	</div>");
		out.print("	<footer>");
		out.print("		<p>crong@codesdj.fff</p>");
		out.print("		<p>http://aaa.aaa.aaa</p>");
		out.print("	</footer>");
		out.print("</body>");
		out.print("</html>");

	}

}
