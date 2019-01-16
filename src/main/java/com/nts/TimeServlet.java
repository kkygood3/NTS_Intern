/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/today")
public class TimeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/M/d HH:mm");

	public TimeServlet() {}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		LocalDateTime currentTime = LocalDateTime.now();

		String currentTimeStr = "현재시간 : " + currentTime.format(formatter);

		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<title>시간을 알려드립니다.</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<a href=\"index.html\">메인화면으로</a>");
		out.println("<h1 style='"
						+ " position: absolute;"
						+ "left: 50%;"
						+ "top: 50%;"
						+ "transform: translate(-50%, -50%);"
						+ "font-size: 40px;'"
						+ "\">"
						+ currentTimeStr + "</h1>");
		out.println("</body>");
		out.println("</html>");

		out.close();
	}

}
