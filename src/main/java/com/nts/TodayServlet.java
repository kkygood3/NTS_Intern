package com.nts;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

/**
 * Servlet implementation class TodayServlet
 */
@WebServlet("/TodayServlet")
public class TodayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public TodayServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html; charset=utf-8;");
		
		LocalDateTime currentTime = java.time.LocalDateTime.now();
		
		int year = currentTime.getYear();
		int month = currentTime.getMonthValue();
		int day = currentTime.getDayOfMonth();
		int hour = currentTime.getHour();
		int minute = currentTime.getMinute();
		
		String dateResult = "<h2>현재시간 : "+year+"/"+month+"/"+day+" "+hour+":"+minute+"</h2>";

		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head><meta charset=\"utf-8\">");
		out.println("<link rel = \"stylesheet\" type = \"text/css\" href = \"date.css\">");
		out.println("<title>Title</title></head>");
		out.println("<body>");
		out.println("<p><a href=\"index.html\">메인화면</a></p>");
		out.println("<section style='text-align:center; padding:40% 0'>");
		out.println(dateResult);
		out.println("</section>");
		out.println("</body>");
		out.println("</html>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
