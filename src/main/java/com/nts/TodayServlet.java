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

@WebServlet("/aboutme/today")
public class TodayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		LocalDateTime localDateTime = LocalDateTime.now();

		String datetime = localDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<a href='/index.html'>메인화면</a>");
		out.print("<h1>");
		out.print("현재시간 : " + datetime);
		out.print("</h1>");
		out.println("</body>");
		out.println("</html>");

		out.close();
	}

}
