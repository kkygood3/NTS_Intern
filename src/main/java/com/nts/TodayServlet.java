package PJT1;
import java.io.PrintWriter;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Servlet implementation class TodayServlet
 */
@WebServlet("/aboutme/today")
public class TodayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String html_send;
	private DateTimeFormatter dtformatter;
    public TodayServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
  
    public void init(ServletConfig config)throws ServletException{
    	html_send=new String();
    	html_send+="<a href=\"/aboutme/index.html\"><h2>메인화면</h2></a>\n";
		html_send+="<h1 style=\"text-align:center; margin-top:150px;\">";
		html_send+="현재시간 : ";
		
		dtformatter = DateTimeFormatter.ofPattern("y/M/d H:m");
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html;charset=utf-8;");
		PrintWriter out = response.getWriter();
		
		String s = new String(html_send);
		s+=LocalDateTime.now().format(dtformatter);
		s+="</h1>\n";
		
		out.print(s);
		out.close();
	}

}
