package src.main.java.com.nts;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javafx.util.converter.LocalDateTimeStringConverter;

import java.time.LocalDateTime;
/**
 * Servlet implementation class TimeServlet
 */
@WebServlet("/time")
public class today extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public today() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		LocalDateTime now = LocalDateTime.now();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.print("<meta charset='utf-8'>");
		out.print("<link rel='stylesheet' href='./layout.css'>");

		out.print("<!doctype html>");
		out.print("<html>");
		out.print("<head>");
		out.print("<title>now time</title>");
		out.print("</head>");

		out.print("<body>");
		out.print("	<header>");
		out.print("		<ul>");
		out.print("			<li><a href='./index.html'><div>홈</div></a></li>");
		out.print("			<li><a href='./aboutme.html'><div>자기소개</div></a></li>");
		out.print("			<li><a href='./photo.html'><div>내사진</div></a></li>");
		out.print("			<li><a href='./get_time'><div>몇시에요</div></a></ul>");
		out.print("		</ul>");
		out.print("	</header>");
		out.print("	<div class='content'>");
		out.print(String.format("현재시간 : %d/%d/%d %d:%d", now.getYear(), now.getMonthValue(), now.getDayOfMonth(), now.getHour(), now.getMinute()));
		out.print("	</div>");
		out.print("	<footer>");
		out.print("		<p>crong@codesdj.fff</p>");
		out.print("		<p>http://aaa.aaa.aaa</p>");
		out.print("	</footer>");
		out.print("</body>");
		out.print("</html>");

	}

}
