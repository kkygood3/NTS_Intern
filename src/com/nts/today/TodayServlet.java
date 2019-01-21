/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.today;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 시간 출력을 담당하는 TodayServlet 클래스입니다.
 * @author  : kkygood3
 */
@WebServlet("/today")
public class TodayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * html코드로 현재시간 출력
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		String currentTime = dateTimeFormatter.format(LocalDateTime.now());

		response.setContentType("text/html;charset=utf-8;");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>Time</title></head>");
		out.println("<style> div {padding-top:200px;} </style>");
		out.println("<body>");
		out.println("<header><a href='index.html'>메인화면</a>");
		out.println("</header>");
		out.println("<div align=\"center\">" +
			"  <h1>현재시간 : ");
		out.println(currentTime);
		out.println("</h1></div>");
		out.println("</body>");
		out.println("</html>");
	}

}
