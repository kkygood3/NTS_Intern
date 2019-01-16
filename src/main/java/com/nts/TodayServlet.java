/** 
 ￿* ￿Copyright￿ 2019 ￿Naver￿Corp. ￿All ￿rights ￿Reserved. 
 ￿* ￿Naver ￿PROPRIETARY/CONFIDENTIAL. ￿Use￿ is ￿subject ￿to ￿license ￿terms.
 ￿*
 * @file   Test.java
 * @package   com.nts
 * @desc   현재 시간 Servlet
 * @author   전연빈
 * @date  2019. 1. 16. 오전 11:15:39
 **/
package com.nts;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/aboutme/today")
public class TodayServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static DateTimeFormatter DATE_FORMAT;
	
	/**
	 * @throws ServletException
	 */
	@Override
	public void init() throws ServletException {
		
		String pattern = "yyyy/MM/dd HH:mm";
		
		// DateTimeFormatter 초기 한번 셋팅
		DATE_FORMAT = new DateTimeFormatterBuilder().appendPattern(pattern).toFormatter();
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		// 현재 시간
		String nowDate = LocalDateTime.now().format(DATE_FORMAT);

		// response setting
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");

		// 출력 객체
		PrintWriter out = response.getWriter();

		// 출력
		out.println("<html>");
		out.println("<head><title>자기소개 page</title></head>");
		out.println("<body>");
		out.println("<div style='font-size: 20px; margin-bottom:30px;'><a href='/aboutme/index.html'>메인화면</a></div>");
		out.println("<div style='text-align: center; font-size: 100px;'>현재시간 : " + nowDate + "</div>");
		out.println("</body>");
		out.println("</html>");
		
		// PrintWriter 버퍼 지우기
		out.flush();
	}

}
