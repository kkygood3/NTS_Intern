/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package today;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/today")
public class Today extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public Today() {}

	// HTML 시간 출력 부분
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		Date time = new Date();

		String time_show = format.format(time);

		response.setContentType("text/html;charset=utf-8;");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>Time</title></head>");
		out.println("<style>" +
			"  div {\r\n" +
			"   padding-top:200px;\r\n" +
			"}\r\n" +
			"  </style>");
		out.println("<body>");
		out.println("<header><a href='index.html'>메인화면</a>");
		out.println("</header>");
		out.println("<div align=\"center\">" +
			"  <h1>현재시간 : ");
		out.println(time_show);
		out.println("</h1></div>");
		out.println("</body>");
		out.println("</html>");
	}

}
