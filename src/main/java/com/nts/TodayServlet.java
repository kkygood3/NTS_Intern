package main.java.com.nts;

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
 * TodayServlet
 * Author : Jaewon Lee, lee.jaewon@nts-corp.com
 */

@WebServlet(name = "Today", urlPatterns = {"/today"})
public class TodayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DateTimeFormatter timeFormatter;

	@Override
	public void init() {
		this.timeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		// Refreshing page every 5 second, for real-time update of the time shown
		response.setIntHeader("Refresh", 5);

		PrintWriter out = response.getWriter();

		out.println(
			"<html>\n" +

				"<head><title> 이재원 </title></head>" +

				"<body bgcolor = '#f0f0f0'>" +

				"<h1 style='" +
				"position: fixed;" +
				" top: 10px;" +
				" left: 10px;" +
				" margin: 0px;'>" +
				"<a href='/aboutme/index.html'>메인메뉴</a>" +
				"</h1>" +

				"<h1 style='" +
				"position: relative;" +
				"float: left;" +
				"top: 50%;" +
				"left: 50%;" +
				"transform: translate(-50%, -50%);'>" +
				"현재시간 : " + LocalDateTime.now().format(timeFormatter) +
				"</h1>" +

				"</body>" +

				"</html>");
		out.close();
	}

}
