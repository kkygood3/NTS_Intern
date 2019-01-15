
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
 * Servlet implementation class TodayServlet
 */
@WebServlet(name = "test", urlPatterns = {"/today"})
public class TodayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TodayServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		// Set response content type
		response.setContentType("text/html");
		// Page character encoding to UTF-8 to serve Korean characters
		response.setCharacterEncoding("UTF-8");
		// Refreshing page every 1 second, for real-time update of the time shown
		response.setIntHeader("Refresh", 1);

		// outstream of response
		PrintWriter out = response.getWriter();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

		//html response structure
		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
		out.println(docType
			+ "<html>\n" + "<head><title> 현재시간 서블릿 페이지 입니다</title></head>\n"
			+ "<h1>"
			+ "<a href = \"/aboutme/index.html\">메인화면</a>"
			+ "</h1>"
			+ "<body bgcolor = \"#f0f0f0\">\n"
			+ "<h1 align = \"center\">"
			+ "현재시간 : " + LocalDateTime.now().format(formatter).toString()
			+ "</h1>\n"
			+ "</h2>\n" + "</body>" + "</html>");
		out.close();
	}

}
