
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
 * Author : Jaewon Lee, lee.jaewon@nts-corp.com
 */

@WebServlet(name = "test", urlPatterns = {"/today"})
public class TodayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DateTimeFormatter timeFormatter;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	// we are NOT using constructor
	// 1. first you cannot declare constructors on interface in Java
	// 2. Servlets are not directly instantiated by Java code, instead container create there instance and keep them in pool

	public TodayServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		/**
		 * @see  DateTimeFormatter#ofPattern();
		 * �Ķ���Ͱ��� �ǰ��Ͽ� LocalDateTime Ŭ������ ���� String���� ��ȯ �����ִ� ������ �����Ѵ�.
		 */
		this.timeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		// Response content type setting
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		// Refreshing page every 5 second, for real-time update of the time shown
		response.setIntHeader("Refresh", 5);

		// outstream of response
		PrintWriter out = response.getWriter();

		// html response structure with in-line style
		out.println("<html>\n" +

			"<head>" +
			"<title> ����ð� ���� ������ �Դϴ�</title>" +
			"</head>\n" +

			"<body bgcolor = '#f0f0f0'>\n" +

			"<h1 style= 'position: fixed; top: 10px; left: 10px; margin: 0px;'>" +
			"<a href='/aboutme/index.html'>����ȭ��</a>" +
			"</h1>" +

			"<h1 style='" +
			"position: relative;" +
			"float: left;" +
			"top: 50%;" +
			"left: 50%;" +
			"transform: translate(-50%, -50%);'>" +
			"����ð� : " + LocalDateTime.now().format(timeFormatter) +
			"</h1>" +

			"</body>" +

			"</html>");
		// html output EOL

	}

}
