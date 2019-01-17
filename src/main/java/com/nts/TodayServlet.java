/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
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
 * @desc 현재 시간 알려주는 servlet
 * @author 전연빈
 */
@WebServlet("/aboutme/today")
public class TodayServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final DateTimeFormatter YYYYMMDDHHMM = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
	
	/**
	 * @desc 현재 시간 클라이언트에게 html형식 출력
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");

		String nowDate = LocalDateTime.now().format(YYYYMMDDHHMM);

		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head><title>자기소개 page</title></head>");
		out.println("<body>");
		out.println("<div style='font-size: 20px; margin-bottom:30px;'><a href='/aboutme/index.html'>메인화면</a></div>");
		out.println("<div style='text-align: center; font-size: 100px;'>현재시간 : " + nowDate + "</div>");
		out.println("</body>");
		out.println("</html>");
		
	}

}
