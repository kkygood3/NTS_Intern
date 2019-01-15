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
 * @desc today servlet
 * @author jyb5858
 */
@WebServlet("/aboutme/today")
public class TodayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TodayServlet() {
		super();
	}

	/**
	 * @param HttpServletRequest,HttpServletResponse
	 * @desc 현재 시간 나타내는 메소드
	 * @method GET
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		// 현재 시간
		String nowDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));

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
	}

}
