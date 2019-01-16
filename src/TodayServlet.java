
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "today", urlPatterns = {"/today"})
public class TodayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TodayServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		// 기본 설정
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		
		PrintWriter writer = response.getWriter();
		writer.print("<a href='view/index.html'>메인화면</a>");
		writer.print("<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>");
		
		// 시간 설정 및 출력
		LocalDateTime time = LocalDateTime.now();
		DateTimeFormatter dateTimeformat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
		String currentTime = "현재시간 : " + time.format(dateTimeformat);
		writer.print("<h1 style='text-align: center;'>" + currentTime + "</h1>");

	}
}
