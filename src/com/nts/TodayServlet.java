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

/*
 * 현재 시간을 표시하는 html페이지를 생성하는 클래스
 * @author si yoon
 */
@WebServlet("/today")
public class TodayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/*
	 * LocalDateTime으로 현재 시간을 표시
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.print("<!doctype html>");
		out.print("<html>");
		out.print("<head>");
		out.print("<meta charset='utf-8'>");
		out.print("<link rel='stylesheet' href='./css/today.css'>");
		out.print("<title>now time</title>");
		out.print("</head>");
		out.print("<body>");
		out.print("<a href='./index.html'>메인으로</a>");
		out.print("<h1><center>현재시간 : " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-M-d HH:mm")) + "<center><h1>");
		out.print("</body>");
		out.print("</html>");

	}

}
