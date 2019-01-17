package com.nts;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/today")
public class TodayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TodayServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8;");

		LocalDateTime currentTime = java.time.LocalDateTime.now();

		String dateResult = "<h2 class=\"date\">현재시간 : " + currentTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"))+ "</h2>";

		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head><meta charset=\"utf-8\"><title>몇시에요</title>");
		out.println("<link href=\"css/today.css\" type=\"text/css\" rel=\"stylesheet\">");
		out.println("</head>");
		out.println("<body>");
		out.println("<p><a href=\"index.html\">메인화면</a></p>");
		out.println("<section class=\"date_section\">");
		out.println(dateResult);
		out.println("</section>");
		out.println("</body>");
		out.println("</html>");
		
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		doGet(request, response);
	}

}
